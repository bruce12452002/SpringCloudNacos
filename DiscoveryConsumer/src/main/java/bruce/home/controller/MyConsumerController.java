package bruce.home.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

@RestController
//@Import(RestTemplate.class)
public class MyConsumerController {
    @Resource
    private LoadBalancerClient client;

    @Resource
    private RestTemplate template;

    @GetMapping("/testConnectProvider")
    public String testConnectProvider(Integer id) {
        // 呼叫微服務
        // 方法一：使用 LoadBalancerClient
//        ServiceInstance serviceInstance = client.choose("provider-cloud"); // 去註冊中心拿微服務的 ID
//        URI uri = serviceInstance.getUri();
//        return template.getForObject(uri + "/test?name=bruce" + id, String.class);

        // 方法二：在 RestTemplate 上加 @LoadBalanced，因為 nacos-discovery 這一包有依賴 ribbon
        return template.getForObject("http://provider-cloud" + "/test?name=bruce" + id, String.class);
    }

    @GetMapping("/testProviderPort")
    public String testProviderPort() {
        // 以下代碼是用方法二的
        return template.getForObject("http://provider-cloud" + "/port", String.class);
    }
}
