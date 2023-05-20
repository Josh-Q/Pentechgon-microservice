package helloworld.api.jwt;

import helloworld.api.constants.Constants;
import helloworld.api.domain.Users;
import helloworld.api.service.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;


@Service
public class JwtTokenConfigsService {

    private final UserService userService;
    // check CSRF
//    private final LoggedInUserService loggedInUserService;

    @Autowired
    public JwtTokenConfigsService(UserService userService) {
        this.userService = userService;
    }

    public String generateToken(Long userId) {
//        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Key key = Keys.hmacShaKeyFor(Constants.Secrets.KEY.getSecretKey().getBytes());

        Instant now = Instant.now();
        Instant expiryInstant = now.plus(15, ChronoUnit.MINUTES); // Set expiry for 15 mins from now
        Date expiryDate = Date.from(expiryInstant);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public Users verifyToken(HttpServletRequest request){
        Key key = Keys.hmacShaKeyFor(Constants.Secrets.KEY.getSecretKey().getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(request.getHeader("Authorization").substring(7))
                .getBody();

        // Get the expiration time from the claims
        long expirationTime = claims.getExpiration().getTime();

        // Check if the token has expired and user is valid
        Optional<Users> optionalUser = userService.findById(Long.valueOf(claims.getSubject()));

        if (optionalUser.isPresent() && expirationTime >= System.currentTimeMillis()) {
            return optionalUser.get();
        }

        throw new IllegalArgumentException("Invalid token");

    }
}
