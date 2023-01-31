package io.github.margato.orders.adapters.`in`.rest.mapper

import io.github.margato.orders.adapters.`in`.rest.models.dto.CustomerDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ProductDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ShippingInfoDto
import io.github.margato.orders.adapters.`in`.rest.models.response.OrderResponse
import io.github.margato.orders.application.entities.Customer
import io.github.margato.orders.application.entities.Order
import io.github.margato.orders.application.entities.Product
import io.github.margato.orders.application.extensions.mapToObject
import io.github.margato.orders.application.valueobjects.ShippingInfo


fun Order.mapToResponse(): OrderResponse =
    mapToObject {
        OrderResponse(
            id = id.toString(),
            customer = customer.mapToResponse(),
            products = products.mapToResponse(),
            shippingInfo = shippingInfo.mapToResponse(),
        )
    }

fun ShippingInfo.mapToResponse(): ShippingInfoDto =
    mapToObject {
        ShippingInfoDto(
            zipCode,
            state,
            streetName,
            city,
            countryCode,
            extraDetail
        )
    }

fun Customer.mapToResponse(): CustomerDto =
    mapToObject {
        CustomerDto(
            id.toString()
        )
    }

fun List<Product>.mapToResponse(): List<ProductDto> =
    mapToObject {
        map { product ->
            ProductDto(
                id = product.id.toString()
            )
        }
    }
