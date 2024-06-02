package edu.katsala.competitionmanager.payload.request;

import lombok.Data;

@Data
public class CreateStadiumRequest {
    private String name;
    private Integer capacity;
    private String address;
}
