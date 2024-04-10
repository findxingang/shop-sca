package pers.xingang.shop.product.service;

import pers.xingang.shop.bean.Product;

/**
 * @author xingang
 * @since 2024/04/10 16:35
 */
public interface ProductService {
    /**
     * 根据商品id获取商品信息
     */
    Product getProductById(Long pid);


    /**
     * 扣减商品库存
     */
    int updateProductStockById(Integer count, Long id);
}
