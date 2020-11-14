package bruce.home.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

@RestController
@Import(RestTemplate.class)
public class MyConsumerController {
    @Resource
    private LoadBalancerClient client;

    @Resource
    private RestTemplate template;

    @GetMapping("/testConnectProvider")
    public String testConnectProvider(Integer id){
        ServiceInstance serviceInstance = client.choose("provider-cloud"); // 去註冊中心拿微服務的 ID
        URI uri = serviceInstance.getUri();
        return template.getForObject(uri + "/test?name=bruce" + id, String.class);
    }
}
