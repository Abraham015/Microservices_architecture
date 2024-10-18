package dev.abraham.ecommerce.kafka.payment;

import dev.abraham.ecommerce.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastname,
        String customerEmail
) {
}