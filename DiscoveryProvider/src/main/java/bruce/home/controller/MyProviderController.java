package bruce.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyProviderController {
    @GetMapping("/test")
    public String test1(String name) {
        return "Hi!" + name;
    }
}
