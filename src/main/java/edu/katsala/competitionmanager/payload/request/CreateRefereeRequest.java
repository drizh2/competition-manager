package edu.katsala.competitionmanager.payload.request;

import lombok.Data;

@Data
public class CreateRefereeRequest {
    private String fullName;
    private Integer age;
    private Integer salary;
}
