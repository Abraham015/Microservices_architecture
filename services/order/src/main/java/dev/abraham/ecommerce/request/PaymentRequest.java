package dev.abraham.ecommerce.request;

import dev.abraham.ecommerce.model.PaymentMethod;
import dev.abraham.ecommerce.response.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}