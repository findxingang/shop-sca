package pers.xingang.shop.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.xingang.shop.bean.Product;

/**
 * @author xingang
 * @since 2024/04/10 16:33
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 扣减商品库存
     */
    int updateProductStockById(@Param("count") Integer count, @Param("id") Long id);
}