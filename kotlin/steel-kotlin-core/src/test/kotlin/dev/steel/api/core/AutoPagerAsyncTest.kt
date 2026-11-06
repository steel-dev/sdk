package dev.steel.api.core

import kotlin.test.assertFailsWith
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AutoPagerAsyncTest {

    private class FakePage(
        private val items: List<String>,
        private val next: (suspend () -> PageAsync<String>)? = null,
    ) : PageAsync<String> {

        var requests = 0

        override fun hasNextPage(): Boolean = next != null

        override suspend fun nextPage(): PageAsync<String> {
            requests++
            return checkNotNull(next).invoke()
        }

        override fun items(): List<String> = items
    }

    @Test
    fun autoPager_collectsEveryPage() = runTest {
        val last = FakePage(listOf("e"))
        val middle = FakePage(listOf("c", "d")) { last }
        val first = FakePage(listOf("a", "b")) { middle }

        assertThat(autoPagerAsync(first).toList()).containsExactly("a", "b", "c", "d", "e")
    }

    @Test
    fun autoPager_stopsAfterTheLastPage() = runTest {
        val only = FakePage(listOf("a"))

        assertThat(autoPagerAsync(only).toList()).containsExactly("a")
        assertThat(only.requests).isZero()
    }

    @Test
    fun autoPager_fetchesNoPageBeyondWhatIsCollected() = runTest {
        val second = FakePage(listOf("c"))
        val first = FakePage(listOf("a", "b")) { second }

        assertThat(autoPagerAsync(first).take(2).toList()).containsExactly("a", "b")
        assertThat(first.requests).isZero()
    }

    @Test
    fun autoPager_rethrowsTheErrorOfTheNextPage() = runTest {
        val first = FakePage(listOf("a")) { throw IllegalStateException("ERROR!") }

        val error = assertFailsWith<IllegalStateException> { autoPagerAsync(first).toList() }
        assertThat(error).hasMessage("ERROR!")
    }
}
