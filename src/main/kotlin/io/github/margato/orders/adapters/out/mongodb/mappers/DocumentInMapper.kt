package io.github.margato.orders.adapters.out.mongodb.mappers

import io.github.margato.orders.adapters.out.mongodb.documents.CustomerDocument
import io.github.margato.orders.adapters.out.mongodb.documents.OrderDocument
import io.github.margato.orders.adapters.out.mongodb.documents.ProductDocument
import io.github.margato.orders.adapters.out.mongodb.documents.ShippingInfoDocument
import io.github.margato.orders.application.entities.Customer
import io.github.margato.orders.application.entities.Order
import io.github.margato.orders.application.entities.Product
import io.github.margato.orders.application.extensions.mapToObject
import io.github.margato.orders.application.valueobjects.ShippingInfo

fun Order.mapToDocument(): OrderDocument =
    mapToObject {
        OrderDocument(
            idempotencyKey = idempotencyKey,
            products = products.mapToDocument(),
            status = status.code,
            customer = customer.mapToDocument(),
            shippingInfo = shippingInfo.mapToDocument()
        )
    }

fun List<Product>.mapToDocument(): List<ProductDocument> =
    mapToObject {
        map { product ->
            ProductDocument(
                product.id
            )
        }
    }


fun Customer.mapToDocument(): CustomerDocument =
    mapToObject {
        CustomerDocument(
            id
        )
    }

fun ShippingInfo.mapToDocument(): ShippingInfoDocument =
    mapToObject {
        ShippingInfoDocument(
            zipCode,
            state,
            streetName,
            city,
            countryCode,
            additionalAddressDetails
        )
    }