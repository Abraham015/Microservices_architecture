package dev.abraham.ecommerce.request;

import dev.abraham.ecommerce.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer is mandatory")
        @NotEmpty
        @NotBlank
        String customerId,
        @NotEmpty(message = "You should purchase a product")
        List<PurchaseRequest> products
) {
}
