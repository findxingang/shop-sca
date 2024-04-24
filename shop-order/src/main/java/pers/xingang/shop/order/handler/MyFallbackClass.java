package pers.xingang.shop.order.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xingang
 * @since 2024/04/24 15:29
 */
@Slf4j
public class MyFallbackClass {

    public static String fallback(Throwable e){
        log.error("异常了:{}", e);
        return "异常了";
    }
}
