package org.example.dto.responses;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class GetSingleImageDataResponse {
    private Boolean hasLiked;
    private String imageAuthor;
    private int imageLikes;
}
