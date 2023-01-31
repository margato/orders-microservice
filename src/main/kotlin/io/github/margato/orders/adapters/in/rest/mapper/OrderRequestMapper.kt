package io.github.margato.orders.adapters.`in`.rest.mapper

import io.github.margato.orders.adapters.`in`.rest.models.dto.CustomerDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ProductDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ShippingInfoDto
import io.github.margato.orders.adapters.`in`.rest.models.request.OrderRequest
import io.github.margato.orders.application.entities.Customer
import io.github.margato.orders.application.entities.Product
import io.github.margato.orders.application.extensions.mapToObject
import io.github.margato.orders.application.valueobjects.ShippingInfo
import io.github.margato.orders.ports.`in`.OrderUseCase


fun OrderRequest.mapToCreateOrderCommand(idempotencyKey: String?): OrderUseCase.CreateOrderCommand =
    mapToObject {
        OrderUseCase.CreateOrderCommand(
            idempotencyKey,
            products.mapToProducts(),
            customer.mapToCustomer(),
            shippingInfo.mapToShippingInfo()
        )
    }

fun ShippingInfoDto.mapToShippingInfo(): ShippingInfo =
    mapToObject {
        ShippingInfo(
            zipCode,
            state,
            streetName,
            city,
            countryCode,
            extraDetail
        )
    }

fun CustomerDto.mapToCustomer(): Customer =
    mapToObject {
        Customer(customerId)
    }

fun List<ProductDto>.mapToProducts(): List<Product> =
    mapToObject {
        map { dto ->
            Product(
                id = dto.id
            )
        }
    }