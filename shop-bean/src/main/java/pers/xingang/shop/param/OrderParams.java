package pers.xingang.shop.param;

import lombok.Data;

/**
 * @author xingang
 * @since 2024/04/10 16:42
 */
@Data
public class OrderParams {
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 购买的商品数量
     */
    private Integer count;

    public boolean isEmpty(){
        return productId == null || productId <= 0 || userId == null || count == null || count <= 0;
    }
}
