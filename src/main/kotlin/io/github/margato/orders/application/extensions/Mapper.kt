package io.github.margato.orders.application.extensions

inline fun <T> mapToObject(
    mapFn: () -> T,
): T {
    return mapFn()
}