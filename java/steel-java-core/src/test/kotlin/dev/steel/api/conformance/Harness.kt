package dev.steel.api.conformance

import com.fasterxml.jackson.databind.JsonNode
import com.sun.net.httpserver.HttpServer
import dev.steel.api.core.jsonMapper
import java.math.BigDecimal
import java.net.InetSocketAddress
import java.net.URLDecoder
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat

internal object Harness {

    private val mapper = jsonMapper()

    private val fixture: JsonNode =
        checkNotNull(Harness::class.java.getResourceAsStream("/conformance.json")).use {
            mapper.readTree(it)
        }

    private val dateTime =
        Regex("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{2}(\\.\\d+)?)?(Z|[+-]\\d{2}:\\d{2})$")

    private val number = Regex("^-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?$")

    private class Recorded(
        val method: String,
        val path: String,
        val query: String,
        val headers: Map<String, List<String>>,
        val body: ByteArray,
    )

    private class Stub(exchanges: List<JsonNode>) : AutoCloseable {

        val requests = mutableListOf<Recorded>()

        private val server: HttpServer = HttpServer.create(InetSocketAddress("127.0.0.1", 0), 0)

        init {
            server.createContext("/") { exchange ->
                val body = exchange.requestBody.readBytes()
                val index =
                    synchronized(requests) {
                        requests.add(
                            Recorded(
                                exchange.requestMethod,
                                exchange.requestURI.rawPath,
                                exchange.requestURI.rawQuery ?: "",
                                exchange.requestHeaders.mapKeys { it.key.lowercase() },
                                body,
                            )
                        )
                        requests.size - 1
                    }
                val reply = exchanges.getOrNull(index)?.get("response")
                if (reply == null) {
                    exchange.sendResponseHeaders(500, -1)
                    exchange.close()
                    return@createContext
                }
                val status = reply["status"].intValue()
                reply["contentType"]
                    ?.takeIf { it.isTextual }
                    ?.let { exchange.responseHeaders.add("Content-Type", it.textValue()) }
                val payload =
                    reply["body"]
                        ?.takeUnless { it.isNull }
                        ?.let { content ->
                            if (content.has("json")) mapper.writeValueAsBytes(content["json"])
                            else content["text"].textValue().toByteArray(Charsets.UTF_8)
                        }
                if (payload == null || status == 204) {
                    exchange.sendResponseHeaders(status, -1)
                } else {
                    exchange.sendResponseHeaders(status, payload.size.toLong())
                    exchange.responseBody.use { it.write(payload) }
                }
                exchange.close()
            }
            server.start()
        }

        val url: String
            get() = "http://127.0.0.1:${server.address.port}"

        override fun close() = server.stop(0)
    }

    private fun scalar(text: String): String =
        when {
            dateTime.matches(text) -> OffsetDateTime.parse(text).toInstant().toString()
            number.matches(text) -> BigDecimal(text).stripTrailingZeros().toPlainString()
            else -> text
        }

    private fun normalize(node: JsonNode?): Any? =
        when {
            node == null || node.isNull || node.isMissingNode -> null
            node.isNumber -> "number:" + node.decimalValue().stripTrailingZeros().toPlainString()
            node.isTextual ->
                node.textValue().let { if (dateTime.matches(it)) "time:" + scalar(it) else it }
            node.isBoolean -> node.booleanValue()
            node.isArray -> node.map { normalize(it) }
            node.isObject ->
                node.fields().asSequence().associate { (key, value) -> key to normalize(value) }
            else -> node.toString()
        }

    private fun decode(text: String): String = URLDecoder.decode(text, "UTF-8")

    private fun query(raw: String): List<String> =
        raw.split('&')
            .filter { it.isNotEmpty() }
            .map { pair ->
                val index = pair.indexOf('=')
                val key = decode(if (index < 0) pair else pair.substring(0, index))
                val value = decode(if (index < 0) "" else pair.substring(index + 1))
                "$key=${scalar(value)}"
            }
            .sorted()

    private fun parts(body: ByteArray, contentType: String): List<String> {
        val match =
            Regex("boundary=(?:\"([^\"]+)\"|([^;]+))", RegexOption.IGNORE_CASE).find(contentType)
                ?: error("multipart body without a boundary: $contentType")
        val boundary = match.groupValues[1].ifEmpty { match.groupValues[2] }
        return String(body, Charsets.ISO_8859_1)
            .split("--$boundary")
            .drop(1)
            .dropLast(1)
            .map { chunk ->
                val trimmed = chunk.removePrefix("\r\n").removeSuffix("\r\n")
                val split = trimmed.indexOf("\r\n\r\n")
                val head = if (split < 0) trimmed else trimmed.substring(0, split)
                val content =
                    String(
                        (if (split < 0) "" else trimmed.substring(split + 4)).toByteArray(
                            Charsets.ISO_8859_1
                        ),
                        Charsets.UTF_8,
                    )
                val disposition =
                    head.lines().firstOrNull { it.startsWith("content-disposition:", true) } ?: ""
                val name =
                    Regex(";\\s*name=\"([^\"]*)\"", RegexOption.IGNORE_CASE)
                        .find(disposition)
                        ?.groupValues
                        ?.get(1) ?: ""
                val file =
                    Regex(";\\s*filename=\"", RegexOption.IGNORE_CASE).containsMatchIn(disposition)
                if (file) "$name=file:$content" else "$name=${scalar(content)}"
            }
            .sorted()
    }

    private fun check(id: String, recorded: Recorded, expected: JsonNode) {
        assertThat(recorded.method)
            .describedAs("$id method")
            .isEqualTo(expected["method"].textValue())
        val path =
            recorded.path
                .split('/')
                .filter { it.isNotEmpty() }
                .map { decode(it.replace("+", "%2B")) }
        assertThat(path).describedAs("$id path").isEqualTo(expected["path"].map { it.textValue() })
        assertThat(query(recorded.query))
            .describedAs("$id query")
            .isEqualTo(
                expected["query"]
                    .map { "${it[0].textValue()}=${scalar(it[1].textValue())}" }
                    .sorted()
            )
        expected["headers"].fields().forEach { (name, value) ->
            assertThat(recorded.headers[name]?.firstOrNull())
                .describedAs("$id header $name")
                .isEqualTo(value.textValue())
        }
        val contentType = recorded.headers["content-type"]?.firstOrNull() ?: ""
        val body = expected["body"]
        when {
            body == null || body.isNull ->
                assertThat(recorded.body).describedAs("$id body").isEmpty()
            body.has("json") -> {
                assertThat(contentType)
                    .describedAs("$id content type")
                    .startsWith("application/json")
                assertThat(normalize(mapper.readTree(recorded.body)))
                    .describedAs("$id body")
                    .isEqualTo(normalize(body["json"]))
            }
            else -> {
                assertThat(contentType)
                    .describedAs("$id content type")
                    .startsWith("multipart/form-data")
                assertThat(parts(recorded.body, contentType))
                    .describedAs("$id parts")
                    .isEqualTo(
                        body["multipart"]
                            .map { part ->
                                if (part.has("file"))
                                    "${part["name"].textValue()}=file:${part["file"].textValue()}"
                                else
                                    "${part["name"].textValue()}=${scalar(part["value"].textValue())}"
                            }
                            .sorted()
                    )
            }
        }
    }

    fun <C> conform(id: String, make: (String, String) -> C, call: (C) -> Any?) {
        val case = fixture["cases"][id] ?: error("unknown conformance case $id")
        val exchanges = case["exchanges"].toList()
        Stub(exchanges).use { stub ->
            val result = call(make(stub.url, fixture["apiKey"].textValue()))
            assertThat(stub.requests).describedAs("$id requests").hasSize(exchanges.size)
            exchanges.forEachIndexed { index, exchange ->
                check(id, stub.requests[index], exchange["request"])
            }
            val expected = case["result"]
            when {
                expected.has("ids") ->
                    assertThat(result)
                        .describedAs("$id ids")
                        .isEqualTo(expected["ids"].map { it.textValue() })
                expected.has("text") ->
                    assertThat(result)
                        .describedAs("$id text")
                        .isEqualTo(expected["text"].textValue())
                expected.has("json") ->
                    assertThat(normalize(mapper.valueToTree<JsonNode>(result)))
                        .describedAs("$id result")
                        .isEqualTo(normalize(expected["json"]))
                else -> {}
            }
        }
    }
}
