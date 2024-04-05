package org.example.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.SessionRequest;
import org.example.entities.SessionContent;
import org.example.entities.SessionHeader;
import org.example.repository.SessionContentRepository;
import org.example.repository.SessionHeaderRepository;
import org.example.services.AgendaService;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService {
    private final SessionHeaderRepository sessionHeaderRepository;
    private final SessionContentRepository sessionContentRepository;

    public Optional<SessionHeader> findById(Long id) {
        return sessionHeaderRepository.findById(id);
    }

    @Transactional
    public SessionHeader update(SessionRequest request, Long id) {
        SessionHeader header = sessionHeaderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session not found"));

        header.setName(request.getName());
        header.setHost(request.getHost());
        header.setLocation(request.getLocation());
        header.setStartTime(request.getStartTime());
        header.setEndTime(request.getEndTime());

        SessionContent content = header.getContent();
        if (content == null) {
            content = new SessionContent();
            header.setContent(content);
        }
        content.setContent(request.getContent());
        //other content fields

        sessionContentRepository.save(content);
        return sessionHeaderRepository.save(header);
}

    @Transactional
    public SessionHeader create (SessionRequest request) {
        SessionHeader header = new SessionHeader();
        header.setName(request.getName());
        header.setHost(request.getHost());
        header.setLocation(request.getLocation());
        header.setStartTime(request.getStartTime());
        header.setEndTime(request.getEndTime());

        if (request.getContent() != null && !request.getContent().isEmpty()) {
            SessionContent content = new SessionContent();
            content.setContent(request.getContent());
            content.setHeader(header);
            header.setContent(content);
        }

        return sessionHeaderRepository.save(header);
    }

}
