package com.miguel.raffles.Raffles;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miguel.raffles.Tickets.Ticket;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String description;


    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String stripeProductId;

    @ElementCollection
    @CollectionTable(name = "raffle_photos", joinColumns = @JoinColumn(name = "raffle_id"))
    @Column(name = "photo_url")
    private Set<String> photoUrls;

    @OneToMany(mappedBy = "raffle", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets;

    private Integer associationId;

}
