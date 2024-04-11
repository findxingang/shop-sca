package pers.xingang.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author binghe
 * @version 1.0.0
 * @description 商品
 */
@Data
@TableName("t_product")
public class Product implements Serializable {
    private static final long serialVersionUID = -2907409980909070073L;
    /**
     * 数据id
     */
    @TableId
    private Long id;

    /**
     * 商品名称
     */
    @TableField("t_pro_name")
    private String proName;

    /**
     * 商品价格
     */
    @TableField("t_pro_price")
    private BigDecimal proPrice;

    /**
     * 商品库存
     */
    @TableField("t_pro_stock")
    private Integer proStock;

}
