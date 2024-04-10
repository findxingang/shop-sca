package pers.xingang.shop.order.service;

import pers.xingang.shop.param.OrderParams;

/**
 * @author xingang
 * @since 2024/04/10 16:42
 */
public interface OrderService {
    /**
     * 保存订单
     */
    void saveOrder(OrderParams orderParams);
}
