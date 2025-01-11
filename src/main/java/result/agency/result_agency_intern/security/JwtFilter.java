package result.agency.result_agency_intern.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import result.agency.result_agency_intern.entity.Role;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.repository.UserRepository;

import java.io.IOException;
import java.util.List;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7).trim(); // Remove "Bearer " and trim whitespace
            try {
                // Validate the token
                if (jwtUtil.isValid(token)) {
                    // Extract username
                    String username = jwtUtil.getUsername(token);

                    // Fetch user from database
                    User user = userRepository.findByUsername(username);

                    if (user != null) {
                        // Get roles or authorities
                        List<Role> roles = jwtUtil.grantedAuthorities(token);

                        // Create authentication object
                        var auth = new UsernamePasswordAuthenticationToken(username, null, roles);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            } catch (Exception e) {
                System.err.println("JWT processing error: " + e.getMessage());
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}

