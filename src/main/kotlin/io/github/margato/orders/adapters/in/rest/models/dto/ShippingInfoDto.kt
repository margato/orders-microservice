package io.github.margato.orders.adapters.`in`.rest.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ShippingInfoDto(
    @JsonProperty("zip_code") val zipCode: String,
    @JsonProperty("state") val state: String,
    @JsonProperty("street_name") val streetName: String,
    @JsonProperty("city") val city: String,
    @JsonProperty("country_code") val countryCode: String,
    @JsonProperty("additional_address_details") val additionalAddressDetails: String,
)
