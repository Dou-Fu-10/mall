package org.example;

import org.example.common.core.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dou-Fu-10 2023/7/6
 *
 * @author Dou-Fu-10
 * @date 2023/7/6
 * @Description 描述
 */
@EnableAsync
@RestController
@SpringBootApplication
@MapperScan(basePackages = {
        "org.example.modules.system.mapper",
        "org.example.modules.comment.mapper",
        "org.example.modules.finance.mapper",
        "org.example.modules.order.mapper",
        "org.example.modules.product.mapper",
        "org.example.modules.tools.mapper",
        "org.example.modules.member.mapper",
})
@EnableTransactionManagement
@EnableScheduling //开启定时任务
public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
