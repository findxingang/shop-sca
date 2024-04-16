package pers.xingang.shop.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.xingang.shop.bean.User;
import pers.xingang.shop.order.fallback.UserServiceFallback;
import pers.xingang.shop.order.fallback.factory.UserServiceFallBackFactory;

/**
 * @author xingang
 * @since 2024/04/11 18:23
 */
// @FeignClient(name = "server-user", fallback = UserServiceFallback.class)
@FeignClient(name = "server-user", fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService {

    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
