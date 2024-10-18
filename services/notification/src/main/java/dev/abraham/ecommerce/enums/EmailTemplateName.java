package dev.abraham.ecommerce.enums;

import lombok.Getter;

@Getter
public enum EmailTemplateName {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order Confirmation");

    private final String templateName;
    private final String subject;
    EmailTemplateName(String templateName, String subject) {
        this.templateName = templateName;
        this.subject = subject;
    }
}
