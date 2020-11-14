package bruce.home.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyProvider2Controller {
    @Value("${server.port}")
    private int port;


    @GetMapping("/port")
    public String port() { // 測試生產者集群
        return "My port is " + port;
    }
}
