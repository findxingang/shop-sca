package pers.xingang.shop.order.service;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pers.xingang.shop.bean.Order;
import pers.xingang.shop.bean.OrderItem;
import pers.xingang.shop.bean.Product;
import pers.xingang.shop.bean.User;
import pers.xingang.shop.order.mapper.OrderItemMapper;
import pers.xingang.shop.order.mapper.OrderMapper;
import pers.xingang.shop.param.OrderParams;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xingang
 * @since 2024/04/10 16:44
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private RestTemplate restTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()){
            throw new RuntimeException("参数异常: " + JSONUtil.toJsonStr(orderParams));
        }

        // 调用用户服务查询用户信息
        User user = restTemplate.getForObject("http://localhost:8060/user/get/" + orderParams.getUserId(), User.class);
        if (user == null){
            throw new RuntimeException("未获取到用户信息: " + JSONUtil.toJsonStr(orderParams));
        }

        // 调用商品服务查询商品信息
        Product product = restTemplate.getForObject("http://localhost:8070/product/get/" + orderParams.getProductId(), Product.class);
        if (product == null){
            throw new RuntimeException("未获取到商品信息: " + JSONUtil.toJsonStr(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()){
            throw new RuntimeException("商品库存不足: " + JSONUtil.toJsonStr(orderParams));
        }

        // 创建订单
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        // 创建订单详情
        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        // 调用商品服务扣减库存
        ResponseEntity result = restTemplate.getForObject("http://localhost:8070/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), ResponseEntity.class);
        assert result != null;
        if (result.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }
}
