package dev.abraham.ecommerce.kafka;

import dev.abraham.ecommerce.kafka.order.OrderConfirmation;
import dev.abraham.ecommerce.kafka.payment.PaymentConfirmation;
import dev.abraham.ecommerce.model.Notification;
import dev.abraham.ecommerce.enums.NotificationType;
import dev.abraham.ecommerce.repository.NotificationRepository;
import dev.abraham.ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
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
    public void consumePaymentSuccessNotification(PaymentConfirmation confirmation) throws MessagingException {
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(confirmation)
                        .build()
        );

        emailService.sendPaymentSuccessEmail(
                confirmation.customerEmail(),
                confirmation.customerFirstName()+" "+confirmation.customerLastname(),
                confirmation.amount(),
                confirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation confirmation) throws MessagingException {
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(confirmation)
                        .build()
        );
        emailService.sendOrderConfirmationEmail(
                confirmation.customer().email(),
                confirmation.customer().firstName()+" "+confirmation.customer().lastName(),
                confirmation.totalAmount(),
                confirmation.orderReference(),
                confirmation.products()
        );
    }
}