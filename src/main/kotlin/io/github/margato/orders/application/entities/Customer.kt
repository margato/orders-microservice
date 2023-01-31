package io.github.margato.orders.application.entities

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Customer(
    val id: String
)
