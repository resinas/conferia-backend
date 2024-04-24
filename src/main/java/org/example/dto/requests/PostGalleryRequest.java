package org.example.dto.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class PostGalleryRequest {
    private MultipartFile file;
}
