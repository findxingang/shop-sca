package pers.xingang.shop.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.xingang.shop.bean.Product;
import pers.xingang.shop.order.fallback.ProductServiceFallback;
import pers.xingang.shop.utils.resp.Result;

/**
 * @author xingang
 * @since 2024/04/11 18:24
 */
@FeignClient(name = "server-product", fallback = ProductServiceFallback.class)
public interface ProductService {

    /**
     * 获取商品信息
     */
    @GetMapping(value = "/product/get/{pid}")
    Product getProduct(@PathVariable("pid") Long pid);

    /**
     * 更新库存数量
     */
    @GetMapping(value = "/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);
}
