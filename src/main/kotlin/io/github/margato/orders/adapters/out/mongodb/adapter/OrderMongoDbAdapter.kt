package io.github.margato.orders.adapters.out.mongodb.adapter

import io.github.margato.orders.adapters.out.mongodb.mappers.mapToDocument
import io.github.margato.orders.adapters.out.mongodb.mappers.mapToDomain
import io.github.margato.orders.adapters.out.mongodb.repository.OrderMongoDbRepository
import io.github.margato.orders.application.entities.Order
import io.github.margato.orders.ports.out.OrderRepository
import jakarta.inject.Singleton
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class OrderMongoDbAdapter(private val repository: OrderMongoDbRepository) : OrderRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun save(order: Order): Mono<Order> =
        repository.save(
            order.mapToDocument()
        ).map {
            it.mapToDomain()
        }

    override fun findById(id: String): Mono<Order> {
        return when (ObjectId.isValid(id)) {
            true -> repository.findById(id)
                .map {
                    it.mapToDomain()
                }

            false -> Mono.empty()
        }
    }

    override fun findByIdempotencyKey(key: String): Mono<Order> =
        repository.findByIdempotencyKey(key)
            .map {
                it.mapToDomain()
            }

    override fun findAll(): Flux<Order> =
        repository.findAll()
            .map {
                it.mapToDomain()
            }
}