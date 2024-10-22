package dev.abraham.ecommerce.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerLogin(
        @NotNull(message = "Customer email is required")
        @Email(message = "Email is not valid")
        String email,
        @NotNull(message = "Password is required")
        String password
) {
}
