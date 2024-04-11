package pers.xingang.shop.order.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xingang.shop.order.service.OrderService;
import pers.xingang.shop.param.OrderParams;

import javax.annotation.Resource;

/**
 * @author xingang
 * @since 2024/04/10 18:16
 */
@Slf4j
@RestController
public class OrderController {
    @Resource(name = "orderServiceImplV3")
    private OrderService orderService;

    @GetMapping(value = "/submit_order")
    public String submitOrder(OrderParams orderParams) {
        log.info("提交订单时传递的参数:{}", JSONUtil.toJsonStr(orderParams));
        orderService.saveOrder(orderParams);
        return "success";
    }
}
