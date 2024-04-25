package pers.xingang.shop.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @author xingang
 * @since 2024/04/17 14:26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayStarter {
    public static void main(String[] args){
        // 添加JVM启动参数，csp.sentinel.app.type = 1 时才会被Sentinel认为是网关项目
        System.setProperty("csp.sentinel.app.type", "1");
        SpringApplication.run(GatewayStarter.class, args);
    }


    @Bean
    @Order
    CommandLineRunner commandLineRunner(Environment environment) {
        return args -> {
            String appName = environment.getProperty("spring.application.name") != null ? environment.getProperty("spring.application.name") : "";
            String port = environment.getProperty("server.port") != null ? environment.getProperty("server.port") : "8080";
            String path = environment.getProperty("server.servlet.context-path") != null ? environment.getProperty("server.servlet.context-path") : "";
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println(
                    "\n\n\t" +
                            "----------------------------------------------------------\n\t" +
                            "Application " + appName +" is running! Access URLs:\n\t" +
                            "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                            "External: \thttp://" + ip + ":" + port + path + "/\n\n\t" +
                            "------------------------------------------------------------"
            );
        };
    }
}
