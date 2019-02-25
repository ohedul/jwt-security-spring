package bd.ohedulalam.jwtsecurityspring.controller;


import bd.ohedulalam.jwtsecurityspring.model.User;
import bd.ohedulalam.jwtsecurityspring.security.JwtGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/token")
public class tokenController {

    private JwtGenerator jwtGenerator;

    public tokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final User user){
        return jwtGenerator.generate(user);

    }
}
