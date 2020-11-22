package bruce.home.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class MyProviderController {
    @Value("${server.port}")
    private int port;


    @GetMapping("/test")
    public String test1(String name) {
        return "Hi!" + name;
    }

    @GetMapping("/port")
    public String port() { // 測試生產者集群
        return "My port is " + port;
    }

    @GetMapping("/testOpenFeignTimeout")
    public String test2() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Success!";
    }
}
