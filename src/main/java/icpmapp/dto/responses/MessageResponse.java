package icpmapp.dto.responses;

import icpmapp.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MessageResponse {
    private Integer id;
    private String title;
    private String message;
    private String author;
    private LocalDateTime date;
}
