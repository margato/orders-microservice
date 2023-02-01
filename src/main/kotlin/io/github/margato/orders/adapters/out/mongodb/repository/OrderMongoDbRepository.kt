package io.github.margato.orders.adapters.out.mongodb.repository

import io.github.margato.orders.adapters.out.mongodb.documents.OrderDocument
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.reactive.ReactorCrudRepository
import reactor.core.publisher.Mono

@MongoRepository
interface OrderMongoDbRepository : ReactorCrudRepository<OrderDocument, String> {
    fun findByIdempotencyKey(key: String): Mono<OrderDocument>
}