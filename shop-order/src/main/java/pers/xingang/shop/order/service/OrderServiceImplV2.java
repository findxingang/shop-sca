package pers.xingang.shop.order.service;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
import pers.xingang.shop.utils.constants.HttpCode;
import pers.xingang.shop.utils.resp.Result;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author xingang
 * @since 2024/04/10 16:44
 */
@Slf4j
@Service
public class OrderServiceImplV2 implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private RestTemplate restTemplate;

    /**
     * add: 注入服务发现类
     */
    @Resource
    private DiscoveryClient discoveryClient;

    // 注意：userServer的值需要与用户微服务下的application.yml文件中的如下配置的值相同。
    private final String userServer = "server-user";
    // 注意：productServer的值需要与商品微服务下的application.yml文件中的如下配置的值相同。
    private final String productServer = "server-product";


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()) {
            throw new RuntimeException("参数异常: " + JSONUtil.toJsonStr(orderParams));
        }


        String userUrl = this.getServiceUrl(userServer);
        String productUrl = this.getServiceUrl(productServer);

        // 调用用户服务查询用户信息
        User user = restTemplate.getForObject("http://" + userUrl + "/user/get/" + orderParams.getUserId(), User.class);
        if (user == null) {
            throw new RuntimeException("未获取到用户信息: " + JSONUtil.toJsonStr(orderParams));
        }

        // 调用商品服务查询商品信息

        Product product = restTemplate.getForObject("http://" + productUrl + "/product/get/" + orderParams.getProductId(), Product.class);
        if (product == null) {
            throw new RuntimeException("未获取到商品信息: " + JSONUtil.toJsonStr(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()) {
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
        Result<Integer> result = restTemplate.getForObject("http://" + productUrl + "/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }

    /**
     * 获取服务实例
     *
     * @param serviceName 服务名
     * @return 主机:端口号
     */
    private String getServiceUrl(String serviceName) {
        // 根据用户名获取所有的服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        // 获取一个服务实例
        ServiceInstance serviceInstance = instances.get(0);
        // 主机:端口号
        return serviceInstance.getHost() + ":" + serviceInstance.getPort();
    }
}
