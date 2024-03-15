package org.example.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
