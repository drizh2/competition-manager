package edu.katsala.competitionmanager.payload.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
}