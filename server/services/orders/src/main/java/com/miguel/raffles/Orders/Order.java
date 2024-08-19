package com.miguel.raffles.Orders;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
@CrossOrigin(origins = "*")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer customerId;


    @Column(nullable = false, unique = true)
    private String orderReference;

    @ElementCollection
    @CollectionTable(name = "order_tickets", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "ticket_id")
    private Set<Integer> ticketsIds;

    @CreationTimestamp
    private LocalDateTime orderDate;

    @PrePersist
    protected void onCreate() {
        this.orderReference = UUID.randomUUID().toString();
    }

}
