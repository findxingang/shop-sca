package pers.xingang.shop.order.fallback;

import org.springframework.stereotype.Component;
import pers.xingang.shop.bean.Product;
import pers.xingang.shop.order.feign.ProductService;
import pers.xingang.shop.utils.resp.Result;

/**
 * 商品服务容错类
 * @author xingang
 * @since 2024/04/16 17:50
 */
@Component
public class ProductServiceFallback implements ProductService {
    @Override
    public Product getProduct(Long pid) {
        // 商品服务出问题时，返回这个商品
        Product product = new Product();
        product.setId(-1L);
        return product;
    }

    @Override
    public Result<Integer> updateCount(Long pid, Integer count) {
        // 商品服务出问题时，返回异常信息提示触发了容错逻辑。
        Result<Integer> result = new Result<>();
        result.setCode(1001);
        result.setCodeMsg("触发了容错逻辑");
        return result;
    }
}
