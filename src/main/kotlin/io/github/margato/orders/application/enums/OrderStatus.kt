package io.github.margato.orders.application.enums


enum class OrderStatus(val code: String, val description: String) {
    CREATED("CREATED", "Order created"),
    CANCELLED("CANCELLED", "Order cancelled"),
    PACKING("PACKING", "Packing order"),
    SHIPPING("SHIPPING", "Shipping order"),
    ON_DELIVERY_ROUTE("DELIVERING", "Order on delivery route"),
    DELIVERED("DELIVERED", "Order delivered")
}