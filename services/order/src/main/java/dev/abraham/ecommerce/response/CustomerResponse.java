package dev.abraham.ecommerce.response;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
