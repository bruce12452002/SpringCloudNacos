package bruce.home.controller;

import bruce.home.bean.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @PostMapping(value = "/testUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadTxtFileOrImage(@RequestPart("file") MultipartFile file) {
        return "uploadTxtFileOrImage success";
    }

    @PostMapping(value = "/testJsonUploadAndTransfer")
    public String JsonUploadAndTransfer(@RequestBody Animal animal) {
        return "bean success!";
    }

    @PostMapping(value = "/testUploadAndJson", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String testUploadAndJson(@RequestBody Animal animal, @RequestPart("file") MultipartFile file) {
        return "beanAndFile success!";
    }
}
