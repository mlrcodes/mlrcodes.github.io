package com.miguel.raffles.Tickets;

import com.miguel.raffles.Raffles.Raffle;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raffle_id")
    private Raffle raffle;

    private Long ticketNumber;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private Integer customerId;

}
