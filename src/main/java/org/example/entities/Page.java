package org.example.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "pages")
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Lob
    private String content;
    private Integer layoutId;

}
