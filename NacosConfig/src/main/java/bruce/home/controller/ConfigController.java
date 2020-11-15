package bruce.home.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // nacos 伺服器一改，不用重啟此微服務就能抓到
@RestController
public class ConfigController {
    @Value("${name1:沒有name1}")
    private String name1;

    @Value("${name2:沒有name2}")
    private String name2;

    @GetMapping("/testConfig")
    public String test() {
        return name1 + "---" + name2;
    }
}
