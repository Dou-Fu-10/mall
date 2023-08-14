package org.example.modules.timedTasks;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.modules.order.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by Dou-Fu-10 2023/8/13
 *
 * @author Dou-Fu-10
 * @date 2023/8/13
 * @Description 定时取消订单
 */
@Slf4j
//@Component
public class CancelOrderTask {

    @Resource
    private OrderService orderService;

    @Scheduled(cron = "0 * * * * *")
        //上一次执行完毕时间点之后5秒再执行
    void testFixedDelayString() {
        log.info("开始批量批量取消未付款订单");
        if (orderService.cancelUnpaidOrdersBulk()) {
            log.info("批量取消未付款订单成功" + +System.currentTimeMillis());
        } else {
            log.info("批量取消未付款订单失败 " + System.currentTimeMillis());
        }
    }

}