package pers.xingang.shop.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xingang
 * @since 2024/04/25 14:30
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class Controller {

    @GetMapping
    public String test() {
        log.info("测试/api");
        return "测试/api";
    }
}
