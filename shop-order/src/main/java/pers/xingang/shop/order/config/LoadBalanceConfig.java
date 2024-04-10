package pers.xingang.shop.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xingang
 * @since 2024/04/10 18:17
 */
@Configuration
public class LoadBalanceConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
