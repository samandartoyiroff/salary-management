package result.agency.result_agency_intern.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.repository.UserRepository;

@Service

public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println("User-  "+user.getAuthorities());
        return user;
    }
}
