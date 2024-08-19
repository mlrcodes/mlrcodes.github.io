package com.miguel.raffles.Notifications;

import com.miguel.raffles.Kafka.Order.OrderConfirmation;
import com.miguel.raffles.Kafka.Payment.PaymentConfirmation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private NotificationType notificationType;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime notificationDate;

    @Column(unique = true)
    private Integer paymentId;

    @Column(unique = true)
    private Integer orderId;

}
