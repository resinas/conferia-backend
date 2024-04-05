package org.example.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionHeaderRequest {
    private Long id;
    private String name;
    private String host;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
