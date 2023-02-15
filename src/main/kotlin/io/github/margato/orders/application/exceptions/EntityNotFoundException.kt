package io.github.margato.orders.application.exceptions

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class EntityNotFoundException(id: String): HttpStatusException(HttpStatus.NOT_FOUND, "Entity not found. ID: $id")