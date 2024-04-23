package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.requests.GetGalleryRequest;
import org.example.dto.requests.PostGalleryRequest;
import org.example.dto.responses.GetGalleryResponse;
import org.example.services.JWTService;
import org.example.services.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/v1/gallery")
@RequiredArgsConstructor
public class GalleryController {
    private final StorageService storageService;
    private final JWTService jwtService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/images")
    public ResponseEntity<GetGalleryResponse> getImagesMetaData(@ModelAttribute GetGalleryRequest getGalleryRequest) throws MalformedURLException {
        return ResponseEntity.ok(storageService.getGalleryImagesMetadata(getGalleryRequest));
    }


    @GetMapping("/image/{filepath}")
    public ResponseEntity<Resource> getImage(@PathVariable String filepath) {
        System.out.println(filepath);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(storageService.getGalleryImage(filepath));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/images")
    public ResponseEntity<String> uploadImages(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, PostGalleryRequest postGalleryRequest) throws IOException {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        storageService.uploadGalleryImages(postGalleryRequest,username);
        return ResponseEntity.ok("Pictures uploaded successfully");
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/images/{id}")
    public ResponseEntity<String> deleteImage(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable Integer imageId) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUserName(token);
        storageService.deleteGalleryImage(username,imageId);
        return ResponseEntity.ok("The image was deleted successfully");
    }
}
