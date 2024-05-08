package org.example.services;

import org.example.dto.responses.AttendeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendeeService {
    Page<AttendeeResponse> getAttendees(Pageable pageable, String search);

    AttendeeResponse getAttendee(Integer id);
}
