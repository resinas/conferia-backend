package org.example.dto.requests;

import lombok.Data;

@Data
public class SessionLikeRequest {
    private Long id;
    private Boolean likes;
}
