package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.SessionRequest;
import org.example.entities.Page;
import org.example.entities.SessionHeader;
import org.example.repository.SessionHeaderRepository;
import org.example.services.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agenda")
@RequiredArgsConstructor
public class AgendaController {
    private final SessionHeaderRepository sessionHeaderRepository;
    private final AgendaService agendaService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<SessionHeader>> getHeaders() {
        List<SessionHeader> headers = sessionHeaderRepository.findAll();
        return ResponseEntity.ok(headers);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SessionHeader> getSessionById(@PathVariable Long id) {
        return ResponseEntity.ok(agendaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found")));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/session")
    public ResponseEntity<SessionHeader> createPage (@RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(agendaService.create(sessionRequest));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<SessionHeader> updatePage (@PathVariable Long id, @RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(agendaService.update(sessionRequest, id));
    }
}
