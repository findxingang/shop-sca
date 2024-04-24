package pers.xingang.shop.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xingang.shop.order.service.SentinelService;

/**
 * @author xingang
 * @since 2024/04/23 18:53
 */
@Slf4j
@RestController
public class SentinelController {

    @Autowired
    private SentinelService sentinelService;

    @GetMapping(value = "/request_sentinel1")
    public String requestSentinel1(){
        log.info("测试Sentinel1");
        sentinelService.sendMessage();
        return "sentinel1";
    }
    @GetMapping(value = "/request_sentinel2")
    public String requestSentinel2() {
        log.info("测试Sentinel2");
        throw new RuntimeException("测试Sentinel2");
        // sentinelService.sendMessage();
        // return "sentinel2";
    }

    @GetMapping(value = "/request_sentinel3")
    @SentinelResource("request_sentinel3")
    public String requestSentinel3(String header, String body){
        log.info("测试Sentinel3");
        return "sentinel3";
    }

    @GetMapping(value = "/request_sentinel4")
    @SentinelResource("request_sentinel4")
    public String requestSentinel4(String serverName){
        log.info("测试Sentinel4");
        return "sentinel4";
    }


    @GetMapping(value = "/request_sentinel5")
    @SentinelResource("request_sentinel5")
    public String requestSentinel5(){
        log.info("测试Sentinel5");
        return "sentinel5";
    }

    @GetMapping(value = "/request_sentinel6")
    public String requestSentinel6(){
        log.info("测试Sentinel6");
        return sentinelService.sendMessage2();
    }
}
