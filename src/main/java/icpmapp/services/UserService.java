package icpmapp.services;


import icpmapp.dto.responses.GetUserResponse;
import icpmapp.dto.responses.UserIdResponse;
import icpmapp.dto.responses.getNameResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

    GetUserResponse getModifiedUserDetails(UserDetails userDetails);

    UserIdResponse getId(String userName);

    getNameResponse getName(int id);

    getNameResponse getName(String username);
}
