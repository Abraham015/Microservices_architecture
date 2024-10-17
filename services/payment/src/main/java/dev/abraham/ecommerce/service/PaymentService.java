package dev.abraham.ecommerce.service;

import dev.abraham.ecommerce.kafka.NotificationProducer;
import dev.abraham.ecommerce.mapper.PaymentMapper;
import dev.abraham.ecommerce.model.Payment;
import dev.abraham.ecommerce.repository.PaymentRepository;
import dev.abraham.ecommerce.request.PaymentNotificationRequest;
import dev.abraham.ecommerce.request.PaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(@Valid PaymentRequest request) {
        Payment payment=paymentRepository.save(paymentMapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}