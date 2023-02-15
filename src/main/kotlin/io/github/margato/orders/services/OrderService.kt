package io.github.margato.orders.services

import io.github.margato.orders.application.entities.Customer
import io.github.margato.orders.application.entities.Order
import io.github.margato.orders.application.entities.Product
import io.github.margato.orders.application.enums.OrderStatus
import io.github.margato.orders.application.exceptions.EntityNotFoundException
import io.github.margato.orders.application.valueobjects.ShippingInfo
import io.github.margato.orders.ports.`in`.OrderUseCase
import io.github.margato.orders.ports.out.OrderRepository
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class OrderService(
    private val orderRepository: OrderRepository,
) : OrderUseCase {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun createOrder(command: OrderUseCase.CreateOrderCommand): Mono<Order> {
        return saveOrderWithIdempotency(command)
    }


    override fun findOrder(id: String): Mono<Order> {
        return orderRepository.findById(id)
            .switchIfEmpty(Mono.error(EntityNotFoundException(id)))
    }

    override fun findOrders(): Flux<Order> {
        return orderRepository.findAll()
    }

    private fun saveOrderWithIdempotency(command: OrderUseCase.CreateOrderCommand): Mono<Order> =
        with(command) {
            when (idempotencyKey.isNullOrBlank()) {
                true -> saveNewOrder(
                    customer = customer,
                    products = products,
                    shippingInfo = shippingInfo
                )

                false -> orderRepository
                    .findByIdempotencyKey(idempotencyKey)
                    .switchIfEmpty(
                        saveNewOrder(
                            idempotencyKey,
                            customer,
                            products,
                            shippingInfo
                        )
                    )
            }
        }

    private fun saveNewOrder(
        idempotencyKey: String? = null,
        customer: Customer,
        products: List<Product>,
        shippingInfo: ShippingInfo,
    ): Mono<Order> =
        orderRepository.save(
            Order(
                status = OrderStatus.CREATED,
                products = products,
                customer = customer,
                shippingInfo = shippingInfo,
                idempotencyKey = idempotencyKey
            )
        )
}