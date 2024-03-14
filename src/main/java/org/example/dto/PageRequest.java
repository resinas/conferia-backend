package org.example.dto;

import lombok.Data;

@Data
public class PageRequest {
    private Integer layoutId;
    private String title;
    private String content;
}
