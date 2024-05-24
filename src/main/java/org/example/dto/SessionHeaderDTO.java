package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entities.SessionHeader;
import org.example.entities.SessionType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SessionHeaderDTO {
    private Long id;
    private String name;
    private String host;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SessionType type;

    public SessionHeaderDTO(SessionHeader sessionHeader) {
        this.id = sessionHeader.getId();
        this.name = sessionHeader.getName();
        this.host = sessionHeader.getHost();
        this.location = sessionHeader.getLocation();
        this.startTime = sessionHeader.getStartTime();
        this.endTime = sessionHeader.getEndTime();
        this.type = sessionHeader.getType();
    }
}
