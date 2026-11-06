package dev.steel.api.core

import java.time.Duration
import kotlinx.coroutines.delay

class DefaultSleeper : Sleeper {

    override fun sleep(duration: Duration) = Thread.sleep(duration.toMillis())

    override suspend fun sleepAsync(duration: Duration) = delay(duration.toMillis())

    override fun close() {}
}
