package edu.katsala.competitionmanager.payload.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCompetitionRequest {
    private String name;
    private String place;
    private LocalDate date;
    private String type;
    private Integer budget;
    private Integer numOfParticipants;
}
