package io.github.margato.orders.adapters.`in`.rest.mapper

import io.github.margato.orders.adapters.`in`.rest.models.dto.CustomerDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ProductDto
import io.github.margato.orders.adapters.`in`.rest.models.dto.ShippingInfoDto
import io.github.margato.orders.adapters.`in`.rest.models.request.OrderRequest
import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec
import java.util.*

class OrderRequestMapperTest : DescribeSpec({

    describe("Test mapping OrderRequest to CreateOrderCommand") {
        it("Should map to command without an idempotency key") {
            val request = OrderRequest(
                CustomerDto(
                    UUID.randomUUID().toString()
                ),
                listOf(
                    ProductDto(UUID.randomUUID().toString())
                ),
                ShippingInfoDto(
                    zipCode = "00000-000",
                    state = "SP",
                    streetName = "My Street",
                    city = "Piracicaba",
                    countryCode = "BR",
                    additionalAddressDetails = "Some information here"
                )
            )

            val command = request.mapToCreateOrderCommand(null)

            command.idempotencyKey shouldBe null

            command.customer.id shouldBe request.customer.customerId

            command.shippingInfo.city shouldBe request.shippingInfo.city
            command.shippingInfo.state shouldBe request.shippingInfo.state
            command.shippingInfo.countryCode shouldBe request.shippingInfo.countryCode
            command.shippingInfo.zipCode shouldBe request.shippingInfo.zipCode
            command.shippingInfo.streetName shouldBe request.shippingInfo.streetName
            command.shippingInfo.additionalAddressDetails shouldBe request.shippingInfo.additionalAddressDetails

            command.products.zip(request.products).forEach { pair ->
                pair.first.id shouldBe pair.second.id
            }
        }

        it("Should map to command with an idempotency key") {
            val request = OrderRequest(
                CustomerDto(
                    UUID.randomUUID().toString()
                ),
                listOf(
                    ProductDto(UUID.randomUUID().toString())
                ),
                ShippingInfoDto(
                    zipCode = "00000-000",
                    state = "SP",
                    streetName = "My Street",
                    city = "Piracicaba",
                    countryCode = "BR",
                    additionalAddressDetails = "Some information here"
                )
            )
            val idempotencyKey = UUID.randomUUID().toString()

            val command = request.mapToCreateOrderCommand(idempotencyKey)

            command.idempotencyKey shouldBe idempotencyKey

            command.customer.id shouldBe request.customer.customerId

            command.shippingInfo.city shouldBe request.shippingInfo.city
            command.shippingInfo.state shouldBe request.shippingInfo.state
            command.shippingInfo.countryCode shouldBe request.shippingInfo.countryCode
            command.shippingInfo.zipCode shouldBe request.shippingInfo.zipCode
            command.shippingInfo.streetName shouldBe request.shippingInfo.streetName
            command.shippingInfo.additionalAddressDetails shouldBe request.shippingInfo.additionalAddressDetails

            command.products.zip(request.products).forEach { pair ->
                pair.first.id shouldBe pair.second.id
            }
        }


    }
})