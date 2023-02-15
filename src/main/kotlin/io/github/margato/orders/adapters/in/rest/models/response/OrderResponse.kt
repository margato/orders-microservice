package io.github.margato.orders.adapters.`in`.rest.models.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.margato.orders.adapters.`in`.rest.models.dto.CustomerDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ProductDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ShippingInfoDto
import io.micronaut.serde.annotation.Serdeable
import java.time.ZonedDateTime

@Serdeable
data class OrderResponse(
    val id: String,
    val customer: CustomerDto,
    val products: List<ProductDto>,
    @JsonProperty("shipping_info") val shippingInfo: ShippingInfoDto,
    @JsonProperty("created_at") val createdAt: ZonedDateTime? = null,
    @JsonProperty("updated_at") val updatedAt: ZonedDateTime? = null,
)
