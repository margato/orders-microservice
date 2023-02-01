package io.github.margato.orders.adapters.out.mongodb.mappers

import io.github.margato.orders.adapters.out.mongodb.documents.CustomerDocument
import io.github.margato.orders.adapters.out.mongodb.documents.OrderDocument
import io.github.margato.orders.adapters.out.mongodb.documents.ProductDocument
import io.github.margato.orders.adapters.out.mongodb.documents.ShippingInfoDocument
import io.github.margato.orders.application.entities.Customer
import io.github.margato.orders.application.entities.Order
import io.github.margato.orders.application.entities.Product
import io.github.margato.orders.application.enums.OrderStatus
import io.github.margato.orders.application.extensions.mapToObject
import io.github.margato.orders.application.valueobjects.ShippingInfo

fun OrderDocument.mapToDomain(): Order =
    mapToObject {
        Order(
            id,
            idempotencyKey,
            products.mapToDomain(),
            OrderStatus.valueOf(status),
            customer.mapToDomain(),
            shippingInfo.mapToDomain()
        )
    }

fun List<ProductDocument>.mapToDomain(): List<Product> =
    mapToObject {
        map { product ->
            Product(
                product.id
            )
        }
    }


fun CustomerDocument.mapToDomain(): Customer =
    mapToObject {
        Customer(
            id
        )
    }

fun ShippingInfoDocument.mapToDomain(): ShippingInfo =
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