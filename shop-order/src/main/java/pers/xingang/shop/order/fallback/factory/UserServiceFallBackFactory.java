package pers.xingang.shop.order.fallback.factory;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import pers.xingang.shop.bean.User;
import pers.xingang.shop.order.feign.UserService;

/**
 * @author xingang
 * @since 2024/04/16 18:03
 */
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable cause) {
        return new UserService() {
            @Override
            public User getUser(Long uid) {
                User user = new User();
                user.setId(-1L);
                return user;
            }
        };
    }
}