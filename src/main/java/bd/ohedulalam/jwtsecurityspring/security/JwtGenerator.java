package bd.ohedulalam.jwtsecurityspring.security;

import bd.ohedulalam.jwtsecurityspring.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generate(User user) {

        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("userId", String.valueOf(user.getUserId()));
        claims.put("role", user.getRole());
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
    }
}
