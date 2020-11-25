package bruce.home.service;

import bruce.home.bean.Animal;
import bruce.home.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "provider-cloud") // 表示這支是用戶端，會呼叫裡面寫的微服務，不需要寫 @Service
public interface MyOpenFeignService {
    // @RequestParam 一定要寫，否則 405，而且參數名稱和 @GetMapping 也必需和提供的微服務方法一致
    @GetMapping("/test")
    String getFeignResult(@RequestParam("name") String xxx);

    @GetMapping("/testOpenFeignTimeout")
    String timeout();

    @PostMapping(value = "/testUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file);

    @PostMapping(value = "/testJsonUploadAndTransfer")
    String jsonUploadAndTransfer(@RequestBody Animal animal);

    // 目前有錯
    @PostMapping(value = "/testUploadAndJson", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String testUploadAndJson(@RequestBody Animal animal, @RequestPart("file") MultipartFile file);
}

// 如果想要使用 gateway 的設定，只要指定 gateway 的微服務名即可
// 再如果 gateway 有設定 predicates -> path 和 filters，可以加在 path 或 @GetMapping
//@FeignClient(value = "gateway-cloud", path = "/xxx")
//public interface MyOpenFeignService {
//    @GetMapping("/test")
//    String getFeignResult(@RequestParam("name") String xxx);
//}