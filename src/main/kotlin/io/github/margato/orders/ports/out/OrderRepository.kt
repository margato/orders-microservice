package io.github.margato.orders.ports.out

import io.github.margato.orders.application.entities.Order
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface OrderRepository {
    fun save(order: Order): Mono<Order>
    fun findById(id: String): Mono<Order>
    fun findByIdempotencyKey(key: String): Mono<Order>
    fun findAll(): Flux<Order>
}