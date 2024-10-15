package dev.abraham.ecommerce.response;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
