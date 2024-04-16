package pers.xingang.shop.order.fallback;

import org.springframework.stereotype.Component;
import pers.xingang.shop.bean.User;
import pers.xingang.shop.order.feign.UserService;

/**
 * 用户服务容错类
 * @author xingang
 * @since 2024/04/16 17:44
 */
@Component
public class UserServiceFallback implements UserService {
    @Override
    public User getUser(Long uid) {
        // 用户服务出问题时返回此用户
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
