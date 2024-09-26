package icpmapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "messages")
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    private String title;
    private String text;
    private LocalDateTime creationTime;

    @ManyToMany(mappedBy = "readMessages")
    private List<User> readBy;
}
