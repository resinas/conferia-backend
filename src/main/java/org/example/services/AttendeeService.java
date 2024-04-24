package org.example.services;

import org.example.dto.responses.AttendeeResponse;

import java.util.List;

public interface AttendeeService {
    List<AttendeeResponse> FetchAll();
}
