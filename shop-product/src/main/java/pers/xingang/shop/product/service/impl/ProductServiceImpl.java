package pers.xingang.shop.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xingang.shop.bean.Product;
import pers.xingang.shop.product.mapper.ProductMapper;
import pers.xingang.shop.product.service.ProductService;

import javax.annotation.Resource;

/**
 * @author xingang
 * @since 2024/04/10 16:36
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Override
    public Product getProductById(Long pid) {
        return productMapper.selectById(pid);
    }

    @Override
    public int updateProductStockById(Integer count, Long id) {
        return productMapper.updateProductStockById(count, id);
    }
}