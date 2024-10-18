package dev.abraham.ecommerce.kafka.order;

import dev.abraham.ecommerce.enums.PaymentMethod;
import dev.abraham.ecommerce.model.Customer;
import dev.abraham.ecommerce.model.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
