package bruce.home;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }

    /**
     * 此方法是要啟用流控模式的鏈路可以使用
     * https://github.com/alibaba/Sentinel/issues/1213
     * 上面的網址有說在某個版本之後用 spring.cloud.sentinel.web-context-unify=false 也可以成功，但我試的根本沒用，也不報錯
     */
    @Bean
    public FilterRegistrationBean<CommonFilter> sentinelFilterRegistration() {
        FilterRegistrationBean<CommonFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        // 入口资源关闭聚合
        registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
        registration.setName("sentinelFilter");
        registration.setOrder(1);
        return registration;
    }

}
