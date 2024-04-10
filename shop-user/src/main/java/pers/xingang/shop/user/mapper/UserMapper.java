package pers.xingang.shop.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xingang.shop.bean.User;

/**
 * 用户Mapper
 * @author xingang
 * @since 2024/04/10 16:25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
