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
        "org.example.modules.admin.system.mapper",
        "org.example.modules.admin.comment.mapper",
        "org.example.modules.portal.member.mapper",
        "org.example.modules.portal.cartItem.mapper",
        "org.example.modules.portal.product.mapper",
        "org.example.modules.admin.order.mapper",
        "org.example.modules.admin.product.mapper",
        "org.example.modules.admin.tools.mapper",
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
