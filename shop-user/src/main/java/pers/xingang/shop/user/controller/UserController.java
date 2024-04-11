package pers.xingang.shop.user.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.xingang.shop.bean.User;
import pers.xingang.shop.user.service.UserService;

import javax.annotation.Resource;

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
}