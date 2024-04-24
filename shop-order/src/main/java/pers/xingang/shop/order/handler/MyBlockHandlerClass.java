package pers.xingang.shop.order.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xingang
 * @since 2024/04/24 15:29
 */
@Slf4j
public class MyBlockHandlerClass {

    public static String blockHandler(BlockException e){
        log.error("限流了:{}", e);
        return "限流了";
    }
}
