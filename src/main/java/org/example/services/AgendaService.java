package org.example.services;

import org.example.dto.SessionRequest;
import org.example.entities.SessionHeader;

import java.util.Optional;

public interface AgendaService {
    Optional<SessionHeader> findById(Long id);

    SessionHeader update(SessionRequest sessionRequest, Long id);

    SessionHeader create(SessionRequest sessionRequest);
}
