package bruce.home.controller;

import bruce.home.service.MyOpenFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MyOpenFeignController {
    @Resource
    private MyOpenFeignService openFeignService;

    @GetMapping("/testOpenFeign")
    public String testOpenFeign(String name) {
        return openFeignService.getFeignResult(name);
    }

    @GetMapping("/testTimeout")
    public String testTimeout() {
        return openFeignService.timeout();
    }
}
