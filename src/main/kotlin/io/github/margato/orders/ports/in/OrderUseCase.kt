package io.github.margato.orders.ports.`in`

import io.github.margato.orders.application.entities.Customer
import io.github.margato.orders.application.entities.Order
import io.github.margato.orders.application.entities.Product
import io.github.margato.orders.application.valueobjects.ShippingInfo
import io.micronaut.serde.annotation.Serdeable
import reactor.core.publisher.Mono

interface OrderUseCase {

    fun createOrder(command: CreateOrderCommand): Mono<Order>
    fun findOrder(id: String): Mono<Order>

    @Serdeable
    data class CreateOrderCommand(
        val idempotencyKey: String?,
        val products: List<Product>,
        val customer: Customer,
        val shippingInfo: ShippingInfo,
    )

}