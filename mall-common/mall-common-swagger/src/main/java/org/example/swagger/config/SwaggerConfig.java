package org.example.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by PanShiFu 2023/7/9
 *
 * @author PanShiFu
 * @date 2023/7/9
 * @Description 描述
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        Info info = new Info();
        info.title("商城系统");
        info.description("电商商城系统");
        info.version("1.0");
        info.contact(new Contact().name("i-kun").url("www.baidu.com"));
        info.license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"));
        return new OpenAPI().info(info);
    }


}