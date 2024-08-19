package com.miguel.raffles.Kafka;

import com.miguel.raffles.Email.EmailService;
import com.miguel.raffles.Kafka.Order.OrderConfirmation;
import com.miguel.raffles.Kafka.Payment.PaymentConfirmation;
import com.miguel.raffles.Notifications.Notification;
import com.miguel.raffles.Notifications.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.miguel.raffles.Notifications.NotificationType.ORDER_CONFIRMATION;
import static com.miguel.raffles.Notifications.NotificationType.PAYMENT_CONFIRMATION;

@RequiredArgsConstructor
@Service
public class NotificationConsumer {

    private final NotificationRepository repository;

    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "paymentGroup")
    public void consumePaymentNotification(
            PaymentConfirmation paymentConfirmation
    ) throws MessagingException {
        repository.save(
                Notification.builder()
                        .paymentId(paymentConfirmation.paymentId())
                        .notificationType(PAYMENT_CONFIRMATION)
                        .build()
        );
        emailService.sendPaymentConfirmationEmail(paymentConfirmation);
    }

    @KafkaListener(topics = "order-topic", groupId = "orderGroup")
    public void consumeOrderNotification(
            OrderConfirmation orderConfirmation
    ) throws MessagingException {
        repository.save(
                Notification.builder()
                        .orderId(orderConfirmation.orderId())
                        .notificationType(ORDER_CONFIRMATION)
                        .build()
        );
        emailService.sendOrderConfirmationEmail(orderConfirmation);
    }
}
