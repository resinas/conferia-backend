package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.responses.AttendeeResponse;
import org.example.entities.User;
import org.example.repository.UserRepository;
import org.example.services.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private UserRepository userRepository;

    public List<AttendeeResponse> FetchAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AttendeeResponse convertToDto(User user) {
        AttendeeResponse dto = new AttendeeResponse();
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        if (Boolean.TRUE.equals(user.getSharingchoice())){
            dto.setAvatarpath(user.getAvatarpath());
            dto.setCompany(user.getCompany());
            dto.setCountry(user.getCountry());
        } else {
            dto.setAvatarpath("default path");
        }
        return dto;
    }
}
