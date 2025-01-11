package result.agency.result_agency_intern.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.LoginDTO;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.repository.UserRepository;
import result.agency.result_agency_intern.security.JwtUtil;
import result.agency.result_agency_intern.service.AuthService;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO) {
        Optional<User> optionalUser = userRepository.findByUsernameAndDeletedFalse(loginDTO.getUsername());
        if (optionalUser.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid username or password");
        }
        var auth = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        authenticationManager.authenticate(auth);
        String username = loginDTO.getUsername();
        String accessToken = "Bearer "+ jwtUtil.generateToken(username);
        return ResponseEntity.ok(accessToken);
    }
}
