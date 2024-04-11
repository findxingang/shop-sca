package pers.xingang.shop.product.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.xingang.shop.bean.Product;
import pers.xingang.shop.product.service.ProductService;
import pers.xingang.shop.utils.constants.HttpCode;
import pers.xingang.shop.utils.resp.Result;

import javax.annotation.Resource;

/**
 * @author xingang
 * @since 2024/04/10 16:37
 */
@RestController
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping(value = "/get/{pid}")
    public Product getProduct(@PathVariable("pid") Long pid){
        Product product = productService.getProductById(pid);
        log.info("获取到的商品信息为：{}", JSONUtil.toJsonStr(product));
        return product;
    }

    @GetMapping(value = "/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count){
        log.info("更新商品库存传递的参数为: 商品id:{}, 购买数量:{} ", pid, count);
        int updateCount = productService.updateProductStockById(count, pid);
        return new Result<>(HttpCode.SUCCESS, "执行成功", updateCount);
    }
}