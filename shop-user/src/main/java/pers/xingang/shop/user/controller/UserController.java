package pers.xingang.shop.user.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.xingang.shop.bean.User;
import pers.xingang.shop.user.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xingang
 * @since 2024/04/10 16:29
 */
@Slf4j
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/get/{uid}")
    public User getUser(@PathVariable("uid") Long uid){
        User user = userService.getUserById(uid);
        log.info("获取到的用户信息为：{}", JSONUtil.toJsonStr(user));
        return user;
    }

    @GetMapping(value = "/api1/demo1")
    public String api1Demo1(){
        log.info("访问了api1Demo1接口");
        return "api1Demo1";
    }
    @GetMapping(value = "/api1/demo2")
    public String api1Demo2(){
        log.info("访问了api1Demo2接口");
        return "api1Demo2";
    }

    @GetMapping(value = "/api2/demo1")
    public String api2Demo1(){
        log.info("访问了api2Demo1接口");
        return "api2Demo1";
    }
    @GetMapping(value = "/api2/demo2")
    public String api2Demo2(){
        log.info("访问了api2Demo2接口");
        return "api2Demo2";
    }

    @GetMapping(value = "/api/filter1")
    public String apiFilter1(HttpServletRequest request, HttpServletResponse response){
        log.info("访问了apiFilter1接口");
        String ip = request.getHeader("IP");
        String name = request.getParameter("name");
        log.info("ip = " + ip + ", name = " + name);
        return "apiFilter1";
    }
}