package dev.abraham.ecommerce.request;

import dev.abraham.ecommerce.model.Customer;
import dev.abraham.ecommerce.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
