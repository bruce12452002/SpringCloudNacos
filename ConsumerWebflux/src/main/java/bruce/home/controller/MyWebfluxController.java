package bruce.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
public class MyWebfluxController {
    @Resource
    private WebClient.Builder webClient;

    @GetMapping("/testWebflux")
    public Mono<String> testWebflux(String name) {
        return webClient.build()
                .get()
                .uri("http://provider-cloud/test?name=" + name)
                .retrieve()
                .bodyToMono(String.class);
    }
}
