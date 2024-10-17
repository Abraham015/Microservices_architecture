package dev.abraham.ecommerce.kafka;

import dev.abraham.ecommerce.kafka.order.OrderConfirmation;
import dev.abraham.ecommerce.kafka.payment.PaymentConfirmation;
import dev.abraham.ecommerce.model.Notification;
import dev.abraham.ecommerce.model.NotificationType;
import dev.abraham.ecommerce.repository.NotificationRepository;
import dev.abraham.ecommerce.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation confirmation){
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(confirmation)
                        .build()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation confirmation){
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(confirmation)
                        .build()
        );
    }
}