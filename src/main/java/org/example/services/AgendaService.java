package org.example.services;

import org.example.dto.SessionDTO;
import org.example.dto.SessionHeaderDTO;
import org.example.entities.SessionHeader;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    SessionDTO findById(Long id);

    SessionHeader update(SessionDTO sessionDTO, Long id);

    SessionHeader create(SessionDTO sessionDTO);

    List<SessionHeaderDTO> fetchAll();

}
