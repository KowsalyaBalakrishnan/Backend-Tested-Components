package in.security.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        System.out.println("Controller => Begin");
        String message = "Welcome!";
        System.out.println("Controller => End");
        return message;
    }

    @GetMapping("/greet")
    public String greet() {
        return "Good Evening!";
    }
}
