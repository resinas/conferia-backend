package icpmapp.controller;

import icpmapp.dto.requests.MessageRequest;
import icpmapp.dto.responses.MessageResponse;
import icpmapp.entities.Message;
import icpmapp.entities.User;
import icpmapp.repository.UserRepository;
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
public class MessageController {

    private final UserRepository userRepository;
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

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @PathVariable Integer id) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);

        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new IllegalArgumentException("invalid email."));

        if (messageService.delete(id, user)) {
            return ResponseEntity.ok("Message deleted.");
        }
        return ResponseEntity.badRequest().body("Message not deleted.");
    }
}
