package dev.abraham.ecommerce.controller;

import dev.abraham.ecommerce.model.Payment;
import dev.abraham.ecommerce.request.PaymentRequest;
import dev.abraham.ecommerce.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest payment) {
        return ResponseEntity.ok(paymentService.createPayment(payment));
    }
}
