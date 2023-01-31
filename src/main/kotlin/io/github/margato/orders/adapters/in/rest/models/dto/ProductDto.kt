package io.github.margato.orders.adapters.`in`.rest.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.margato.orders.application.annotations.ValidUUID
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ProductDto(
    @JsonProperty("product_id") @ValidUUID val id: String,
)
