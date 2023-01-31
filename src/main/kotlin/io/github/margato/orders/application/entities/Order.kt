package io.github.margato.orders.application.entities

import io.github.margato.orders.application.enums.OrderStatus
import io.github.margato.orders.application.valueobjects.ShippingInfo
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Order(
    val id: String? = null,
    val idempotencyKey: String? = null,
    val products: List<Product>,
    val status: OrderStatus,
    val customer: Customer,
    val shippingInfo: ShippingInfo,
)

