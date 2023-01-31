package io.github.margato.orders.application.valueobjects

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ShippingInfo(
    val zipCode: String,
    val state: String,
    val streetName: String,
    val city: String,
    val countryCode: String,
    val extraDetail: String,
)
