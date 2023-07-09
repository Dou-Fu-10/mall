package org.example;

import org.example.common.core.utils.SpringContextHolder;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PanShiFu 2023/7/6
 *
 * @author PanShiFu
 * @date 2023/7/6
 * @Description 描述
 */
@EnableAsync
@RestController
@SpringBootApplication
@MapperScan(basePackages = {
        "org.example.modules.system.mapper",
})
@EnableTransactionManagement
public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
    /**
     * 访问首页提示
     *
     * @return /
     */
    @AnonymousGetMapping("/")
    public String index() {
        return "Backend service started successfully";
    }
}
