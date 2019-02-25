package bd.ohedulalam.jwtsecurityspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/api")
@RestController
public class securityController {

    @GetMapping
    public String hello(){
        return "Hello world!";
    }
}
