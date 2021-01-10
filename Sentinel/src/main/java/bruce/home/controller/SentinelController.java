package bruce.home.controller;

import bruce.home.service.SentinelService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class SentinelController {
    @Resource
    private SentinelService sentinelService;

    @GetMapping("/testSentinel")
    public String testSentinel() {
        return "hello sentinel";
    }

    @GetMapping("/testThread")
    public String testThread() {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaaaaaaaaaa");
        return "hello thread";
    }

    @GetMapping("/testRelated")
    public String testRelated() {
        return "hello related";
    }

    @GetMapping("/testChain1")
    public String testChain1a() {
        return sentinelService.testChain();
    }

    @GetMapping("/testChain2")
    public String testChain1b() {
        return sentinelService.testChain();
    }

}
