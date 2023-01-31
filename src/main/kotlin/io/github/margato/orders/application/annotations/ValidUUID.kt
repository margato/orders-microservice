package io.github.margato.orders.application.annotations

import javax.validation.constraints.Pattern

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}\$", message = "Must be a valid UUID")
annotation class ValidUUID
