package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.SessionDTO;
import org.example.dto.SessionHeaderDTO;
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
    @GetMapping("/sessions")
    public ResponseEntity<List<SessionHeaderDTO>> getHeaders() {
        List<SessionHeaderDTO> headers = agendaService.fetchAll();
        return ResponseEntity.ok(headers);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/session/{id}")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable Long id) {
        SessionDTO response = agendaService.findById(id);
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/session/create")
    public ResponseEntity<SessionHeader> createPage (@RequestBody SessionDTO sessionDTO) {
        return ResponseEntity.ok(agendaService.create(sessionDTO));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/session/update/{id}")
    public ResponseEntity<SessionHeader> updatePage (@PathVariable Long id, @RequestBody SessionDTO sessionDTO) {
        return ResponseEntity.ok(agendaService.update(sessionDTO, id));
    }
}
