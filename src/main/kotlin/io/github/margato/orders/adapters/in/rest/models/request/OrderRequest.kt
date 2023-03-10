package io.github.margato.orders.adapters.`in`.rest.models.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.margato.orders.adapters.`in`.rest.models.dto.CustomerDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ProductDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ShippingInfoDto
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class OrderRequest(
    val customer: CustomerDto,
    val products: List<ProductDto>,
    @JsonProperty("shipping_info") val shippingInfo: ShippingInfoDto,
)
