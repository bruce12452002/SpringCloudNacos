package bruce.home.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("provider-cloud") // 表示這支是用戶端，會呼叫裡面寫的微服務，不需要寫 @Service
public interface MyOpenFeignService {
    // @RequestParam 一定要寫，否則 405，而且參數名稱和 @GetMapping 也必需和提供的微服務方法一致
    @GetMapping("/test")
    String getFeignResult(@RequestParam("name") String xxx);
}
