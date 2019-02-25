package bd.ohedulalam.jwtsecurityspring.security;

import bd.ohedulalam.jwtsecurityspring.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "secret";




    public User validate(String token) {

        User jwtUser = null;
        try {
            Claims body = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody();

            jwtUser = new User();
            jwtUser.setUserName(body.getSubject());
            jwtUser.setUserId(Integer.parseInt((String)body.get("userId")));
            jwtUser.setRole((String)body.get("roles"));

        }catch (Exception e){
            System.out.println(e);
        }

        return jwtUser;
    }
}
