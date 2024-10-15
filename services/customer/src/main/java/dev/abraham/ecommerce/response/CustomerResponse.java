package dev.abraham.ecommerce.response;

import dev.abraham.ecommerce.model.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
