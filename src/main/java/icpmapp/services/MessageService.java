package icpmapp.services;

import icpmapp.dto.requests.MessageRequest;
import icpmapp.dto.responses.AttendeeResponse;
import icpmapp.dto.responses.MessageResponse;
import icpmapp.entities.Message;
import icpmapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    List<MessageResponse> getMessages();

    Message create(MessageRequest messageRequest, String username);

    boolean delete(Integer id, User userRequestingDelete);
}
