package pers.xingang.shop.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xingang.shop.bean.User;
import pers.xingang.shop.user.mapper.UserMapper;
import pers.xingang.shop.user.service.UserService;

import javax.annotation.Resource;

/**
 * @author xingang
 * @since 2024/04/10 16:28
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}