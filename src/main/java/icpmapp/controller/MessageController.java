package icpmapp.controller;

import icpmapp.dto.requests.JwtAuthenticationResponse;
import icpmapp.dto.requests.MessageRequest;
import icpmapp.dto.requests.PageRequest;
import icpmapp.dto.responses.MessageResponse;
import icpmapp.entities.Message;
import icpmapp.entities.Page;
import icpmapp.repository.MessageRepository;
import icpmapp.services.JWTService;
import icpmapp.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {

    private final MessageService messageService;
    private final JWTService jwtService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Message> createMessage (
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestBody MessageRequest messageRequest) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        return ResponseEntity.ok(messageService.create(messageRequest, username));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<MessageResponse>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }
}
