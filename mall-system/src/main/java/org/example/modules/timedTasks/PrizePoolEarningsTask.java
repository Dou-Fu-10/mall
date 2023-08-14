package org.example.modules.timedTasks;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.modules.finance.service.PrizePoolService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Dou-Fu-10 2023/8/13
 *
 * @author Dou-Fu-10
 * @date 2023/8/13
 * @Description 定时备份数据库
 */
@Slf4j
//@Component
public class PrizePoolEarningsTask {
    @Resource
    private PrizePoolService prizePoolService;

    // 每天凌晨3点执行备份任务
//    @Scheduled(cron = "0 0 3 * * ?")
    @Scheduled(cron = "0 * * * * *")
    public void backupDatabase() {

        log.info("生成当天收益");
        if (prizePoolService.IncomeCalculation()) {
            log.info("生成当天收益" + +System.currentTimeMillis());
        } else {
            log.info("生成当天收益 " + System.currentTimeMillis());
        }
    }
}
