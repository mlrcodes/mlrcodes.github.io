package com.miguel.raffles.Orders;

public enum OrderStatus {
    PENDING_PAYMENT,   // Pendiente de pago
    PAID,              // Pago completado con éxito
    PAYMENT_FAILED,    // Fallo en el pago
    CANCELLED,         // Pedido cancelado por el usuario o por un error
    COMPLETED,         // Pedido completado, todo el proceso finalizado
    EXPIRED,
    PROCESSING         // Pedido en proceso de verificación o pasos adicionales
}
