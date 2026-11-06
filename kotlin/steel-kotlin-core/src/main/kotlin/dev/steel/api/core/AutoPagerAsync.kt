package dev.steel.api.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@JvmSynthetic
internal fun <T> autoPagerAsync(firstPage: PageAsync<T>): Flow<T> = flow {
    var page = firstPage
    while (true) {
        for (item in page.items()) {
            emit(item)
        }
        if (!page.hasNextPage()) {
            break
        }
        page = page.nextPage()
    }
}
