package az.test.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerV1 {

    @RequestMapping("/")
    public String index() {
        return "Spring web application. Hello!";
    }
}
