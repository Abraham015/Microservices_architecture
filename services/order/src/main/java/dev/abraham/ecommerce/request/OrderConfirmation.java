package dev.abraham.ecommerce.request;

import dev.abraham.ecommerce.model.PaymentMethod;
import dev.abraham.ecommerce.response.CustomerResponse;
import dev.abraham.ecommerce.response.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
