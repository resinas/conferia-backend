package org.example.controller;

import java.io.IOException;
import org.example.services.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.example.dto.responses.AttendeeResponse;
import org.example.services.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;
    private StorageService storageService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Page<AttendeeResponse>> GetAttendees(@RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "200") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(attendeeService.GetAttendees(pageable));
    }

    @GetMapping("/avatar/{username}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Resource> getImage(@PathVariable String username, @RequestParam(name = "format") String format) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or the appropriate content type
                .body(storageService.getProfileImage(username, format));
    }

}
