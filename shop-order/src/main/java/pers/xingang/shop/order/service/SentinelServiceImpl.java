package pers.xingang.shop.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.xingang.shop.order.handler.MyBlockHandlerClass;
import pers.xingang.shop.order.handler.MyFallbackClass;

/**
 * @author xingang
 * @since 2024/04/23 18:52
 */
@Service
@Slf4j
public class SentinelServiceImpl implements SentinelService{
    @Override
    @SentinelResource("sendMessage")
    public void sendMessage() {
        System.out.println("测试Sentinel的链路流控模式");
    }

    private int count = 0;

    @Override
    @SentinelResource(
            value = "sendMessage2",
            blockHandlerClass = MyBlockHandlerClass.class,
            blockHandler = "blockHandler",
            fallbackClass = MyFallbackClass.class,
            fallback = "fallback")
    public String sendMessage2() {
        count ++;
        System.out.println(count);
        //25%的异常率
        if (count % 4 == 0){
            throw new RuntimeException("25%的异常率");
        }
        return "sendMessage2";
    }
}
