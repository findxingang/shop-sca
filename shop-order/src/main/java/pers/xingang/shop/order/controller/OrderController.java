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
    @Resource(name = "orderServiceImplV6")
    private OrderService orderService;

    @GetMapping(value = "/submit_order")
    public String submitOrder(OrderParams orderParams) {
        log.info("提交订单时传递的参数:{}", JSONUtil.toJsonStr(orderParams));
        orderService.saveOrder(orderParams);
        return "success";
    }



    int sum = 0;
    /**
     * 首先需要限制Tomcat的线程数，否则观察不到接口响应慢
     * 模拟一个并发的场景
     */
    @GetMapping(value = "/concurrent_request")
    public String concurrentRequest() {
        log.info("测试业务在高并发场景下是否存在问题：{}", ++sum);
        return "success";
    }


    @GetMapping(value = "/test_sentinel")
    public String testSentinel(){
        log.info("测试Sentinel");
        return "sentinel";
    }


    /**
     * 测试Sentinel关联流量控制
     * @return
     */
    @GetMapping(value = "/test_sentinel2")
    public String testSentinel2(){
        log.info("测试Sentinel2");
        return "sentinel2";
    }
}
