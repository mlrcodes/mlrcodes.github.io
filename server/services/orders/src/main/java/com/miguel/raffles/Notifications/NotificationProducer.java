package com.miguel.raffles.Notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, OrderNotificationRequest> kafkaTemplate;

    public void sendOrderConfirmation(OrderNotificationRequest orderConfirmation) {
        Message<OrderNotificationRequest> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(TOPIC, "order-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
