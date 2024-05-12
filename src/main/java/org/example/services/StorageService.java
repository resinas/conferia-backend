package org.example.services;

import org.example.dto.requests.DeleteGalleryRequest;
import org.example.dto.requests.PostGalleryRequest;
import org.example.dto.responses.GetGalleryResponse;
import org.example.dto.responses.GetSingleImageDataResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void storeProfileImage(MultipartFile file, String username) throws IOException;

    Resource getProfileImage(Integer id, String format) throws IOException;

    GetGalleryResponse getGalleryImagesMetadata(int pageNr, int pageSize, String search, String filterChoice, boolean orderValue);

    GetSingleImageDataResponse getGalleryImageSingleData (String filepath, String username);

    GetGalleryResponse getMyGalleryImagesMetadata(String username);
    Resource getGalleryImage(String filepath, String format);

    void uploadGalleryImages(PostGalleryRequest postGalleryRequest, String username) throws IOException;

    void deleteGalleryImage(String username, DeleteGalleryRequest deleteGalleryRequest);

    void changeLikeStatusForGalleryImage(Boolean likes, String username, String filePath);
}
