package pers.xingang.shop.user.service;

import pers.xingang.shop.bean.User;

/**
 * @author xingang
 * @since 2024/04/10 16:28
 */
public interface UserService {

    /**
     * 根据id获取用户信息
     */
    User getUserById(Long userId);
}
