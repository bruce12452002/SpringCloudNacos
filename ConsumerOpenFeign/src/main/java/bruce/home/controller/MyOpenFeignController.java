package bruce.home.controller;

import bruce.home.bean.Animal;
import bruce.home.service.MyOpenFeignService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/testUploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadTxtFileOrImage(@RequestPart("file") MultipartFile file) {
        return openFeignService.uploadFile(file);
    }

    @PostMapping(value = "/testJson", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    String jsonUploadAndTransfer(@RequestPart("json") Animal animal) {
        return openFeignService.jsonUploadAndTransfer(animal);
    }

    @PostMapping(value = "/testUploadAndJson", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    String testUploadAndJson(@RequestPart("file") MultipartFile file,
                             @RequestPart("json") Animal animal) {
        return openFeignService.testUploadAndJson(animal, file);
    }

}
