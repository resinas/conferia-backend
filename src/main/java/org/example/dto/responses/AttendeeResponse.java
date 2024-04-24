package org.example.dto.responses;

import lombok.Data;

@Data
public class AttendeeResponse {
    private String firstname;
    private String lastname;
    private String avatarpath;
    private String company;
    private String country;

}
