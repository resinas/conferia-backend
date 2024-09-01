package icpmapp.dto.responses;

import lombok.Data;

@Data
public class GetSingleImageDataResponse {
    private Boolean hasLiked;
    private String imageAuthor;
    private int imageLikes;
}
