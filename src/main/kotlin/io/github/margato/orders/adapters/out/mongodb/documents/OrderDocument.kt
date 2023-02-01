package io.github.margato.orders.adapters.out.mongodb.documents

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

@MappedEntity("Orders")
data class OrderDocument(
    @field:Id
    @field:GeneratedValue
    val id: String? = null,
    val idempotencyKey: String? = null,
    val products: List<ProductDocument>,
    val status: String,
    val customer: CustomerDocument,
    val shippingInfo: ShippingInfoDocument,
)

@Serdeable
data class CustomerDocument(
    val id: String,
)

@Serdeable
data class ProductDocument(
    val id: String,
)

@Serdeable
data class ShippingInfoDocument(
    val zipCode: String,
    val state: String,
    val streetName: String,
    val city: String,
    val countryCode: String,
    val extraDetail: String,
)
