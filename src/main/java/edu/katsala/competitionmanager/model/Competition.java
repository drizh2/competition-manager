package edu.katsala.competitionmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String place;
    private LocalDate date;
    private String type;
    private Integer budget;
    private Integer numOfParticipants;

    @ManyToOne
    private Referee referee;
    @ManyToOne
    private Stadium stadium;
    @ManyToOne
    private User user;
}
