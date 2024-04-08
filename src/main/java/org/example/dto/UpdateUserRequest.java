package org.example.dto;

import lombok.Data;

import java.net.URL;

@Data
public class UpdateUserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String country;
    private String company;
    private boolean sharingChoice;
}
