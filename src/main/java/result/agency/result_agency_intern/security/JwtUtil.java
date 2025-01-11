package result.agency.result_agency_intern.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.entity.Role;
import result.agency.result_agency_intern.entity.User;
import result.agency.result_agency_intern.repository.UserRepository;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service

public class JwtUtil {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Value("${security.secure.key}")
    private String secretKey;



    // Access token 30 minut
    public String generateToken(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("mehrli_maktab_admin")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))
                .signWith(getSignKey())
                .claim("roles", userDetails.getAuthorities())
                .compact();

    }

    private Key getSignKey() {
        byte[] decode = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(decode);
    }

    public boolean isValid(String token) {
        Claims claims = getClaims(token);
        return true;
    }

    public String getUsername(String token){

        Claims claims = getClaims(token);
        return claims.getSubject();

    }
    public List<Role> grantedAuthorities(String token){

        Claims claims = getClaims(token);
        String username = claims.getSubject();
        User byUsername = userRepository.findByUsername(username);
        return null;
    }
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Refresh token 1 yil
    public String generateRefreshToken(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("funn_app")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*365))
                .signWith(getSignKey())
                .claim("roles", userDetails.getAuthorities())
                .compact();

    }
}
