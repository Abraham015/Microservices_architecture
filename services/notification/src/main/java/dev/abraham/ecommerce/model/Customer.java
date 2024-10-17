package dev.abraham.ecommerce.model;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
