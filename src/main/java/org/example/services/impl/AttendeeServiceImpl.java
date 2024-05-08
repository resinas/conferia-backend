package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.responses.AttendeeResponse;
import org.example.entities.User;
import org.example.repository.UserRepository;
import org.example.services.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private UserRepository userRepository;

    public Page<AttendeeResponse> getAttendees(Pageable pageable, String search) {
        Page<User> page;
        if (search != null && !search.trim().isEmpty()) {
            page = userRepository.searchWithConditionalPrivacy(search, pageable);
        } else {
            page = userRepository.findAll(pageable);
        }
        return page.map(this::convertToDto);
    }

    public AttendeeResponse getAttendee(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return convertToDto(user);
    }

    private AttendeeResponse convertToDto(User user) {
        AttendeeResponse dto = new AttendeeResponse();
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setId(user.getId());
        if (Boolean.TRUE.equals(user.getSharingchoice())){
            dto.setAvatar_path(user.getAvatar_path());
            dto.setCompany(user.getCompany());
            dto.setCountry(user.getCountry());
            dto.setEmail(user.getEmail());
        }
        return dto;
    }
}
