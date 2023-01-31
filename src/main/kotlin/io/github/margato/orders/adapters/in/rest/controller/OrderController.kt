package io.github.margato.orders.adapters.`in`.rest.controller

import io.github.margato.orders.adapters.`in`.rest.mapper.mapToCreateOrderCommand
import io.github.margato.orders.adapters.`in`.rest.mapper.mapToResponse
import io.github.margato.orders.adapters.`in`.rest.models.request.OrderRequest
import io.github.margato.orders.adapters.`in`.rest.models.response.OrderResponse
import io.github.margato.orders.common.Constants
import io.github.margato.orders.ports.`in`.OrderUseCase
import io.micronaut.http.HttpHeaders
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import reactor.core.publisher.Mono
import java.util.*
import javax.validation.Valid

@Controller("/orders")
open class OrderController(
    private val orderUseCase: OrderUseCase,
) {

    @Post
    open fun create(@Body request: OrderRequest, headers: HttpHeaders): Mono<OrderResponse> {
        val idempotencyKey = headers.get(Constants.IDEMPOTENCY_KEY)

        return orderUseCase
            .createOrder(request.mapToCreateOrderCommand(idempotencyKey))
            .map { it.mapToResponse() }
    }

    @Get("/{id}")
    @Valid
    open fun getById(id: String): Mono<OrderResponse> {
        return orderUseCase
            .findOrder(id)
            .map { it.mapToResponse() }
    }

}