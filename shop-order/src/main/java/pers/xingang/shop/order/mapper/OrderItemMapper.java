package pers.xingang.shop.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xingang.shop.bean.Order;
import pers.xingang.shop.bean.OrderItem;

/**
 * @author xingang
 * @since 2024/04/10 16:41
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
