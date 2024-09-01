package icpmapp.services.impl;

import icpmapp.dto.requests.MessageRequest;
import icpmapp.dto.responses.AttendeeResponse;
import icpmapp.dto.responses.MessageResponse;
import icpmapp.entities.Message;
import icpmapp.entities.Page;
import icpmapp.entities.User;
import icpmapp.repository.MessageRepository;
import icpmapp.repository.UserRepository;
import icpmapp.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RestClientAutoConfiguration restClientAutoConfiguration;

    @Override
    public List<MessageResponse> getMessages() {
        List<Message> list = messageRepository.findAll(Sort.by(Sort.Direction.DESC, "creationTime"));
        return list.stream().map(this::convertToDto).toList();
    }

    @Override
    public Message create(MessageRequest messageRequest, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + username));

        Message msg = new Message();
        msg.setAuthor(user);
        msg.setTitle(messageRequest.getTitle());
        msg.setText(messageRequest.getText());
        msg.setCreationTime(LocalDateTime.now());

        return messageRepository.save(msg);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Optional<Message> findById(Integer id) {
        return Optional.empty();
    }

    private MessageResponse convertToDto(Message msg) {
        MessageResponse resp = new MessageResponse();
        resp.setId(msg.getId());
        resp.setTitle(msg.getTitle());
        resp.setMessage(msg.getText());
        resp.setAuthor(msg.getAuthor().getFirstname() + " " + msg.getAuthor().getLastname());
        resp.setAvatar(msg.getAuthor().getAvatar_path());
        resp.setDate(msg.getCreationTime());
        return resp;
    }
}
