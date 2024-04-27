package org.example.services;

import org.example.dto.requests.GetGalleryRequest;
import org.example.dto.requests.PostGalleryRequest;
import org.example.dto.responses.GetGalleryResponse;
import org.example.dto.responses.GetSingleImageDataResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface StorageService {
    void storeProfileImage(MultipartFile file, String username) throws IOException;

    Resource getProfileImage(String username, String format) throws IOException;

    GetGalleryResponse getGalleryImagesMetadata(GetGalleryRequest getGalleryRequest) throws MalformedURLException;

    GetSingleImageDataResponse getGalleryImageSingleData (String filepath, String username);

    GetGalleryResponse getMyGalleryImagesMetadata(String username);
    Resource getGalleryImage(String filepath, String format);

    void uploadGalleryImages(PostGalleryRequest postGalleryRequest, String username) throws IOException;

    void deleteGalleryImage(String username, Integer imageId);
}
