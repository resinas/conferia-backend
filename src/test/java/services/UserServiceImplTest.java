package services;

import org.example.entities.User;
import org.example.repository.UserRepository;
import org.example.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        // Given
        String email = "user@email.com";
        User mockUser = new User();
        mockUser.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        // When
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);

        // Then
        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        verify(userRepository).findByEmail(email);
    }

    @Test
    void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFound() {
        String email = "user@email.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When
        UsernameNotFoundException thrown = assertThrows(
                UsernameNotFoundException.class,
                () -> userService.userDetailsService().loadUserByUsername(email),
                "Expected loadUserByUsername to throw, but it didn't"
        );

        // Then
        assertTrue(thrown.getMessage().contains("User not found"));
        verify(userRepository).findByEmail(email);
    }
}
