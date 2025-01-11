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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String username=null;
        if (token!=null){
             username = jwtUtil.getUsername(token);
        }
        User user = userRepository.findByUsername(username);
        if (user!=null) {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                if (jwtUtil.isValid(token)){
                    List<Role> roles = jwtUtil.grantedAuthorities(token);
                    System.out.println("Roles-"+roles);
                    var auth = new UsernamePasswordAuthenticationToken(
                            jwtUtil.getUsername(token), null,
                            roles
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}
