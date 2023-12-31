package org.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Dou-Fu-10 2023/7/9
 *
 * @author Dou-Fu-10
 * @date 2023/7/9
 * @Description 描述
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        Info info = new Info();
        info.title("商城前台系统");
        info.description("电商商城系统");
        info.version("1.0");
        info.contact(new Contact().name("Dou-Fu-10").url("https://github.com/Dou-Fu-10"));
        info.license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"));
        return new OpenAPI().info(info);
    }

    private String grouped(String api) {
        return "/" + api + "/**";
    }

    private String groupedApp(String api) {
        return "/app/" + api + "/**";
    }

    @Bean
    public GroupedOpenApi cart() {
        return GroupedOpenApi.builder()
                .group("购物车")
                .pathsToMatch(groupedApp("cart"))
                .build();
    }

    @Bean
    public GroupedOpenApi comment() {
        return GroupedOpenApi.builder()
                .group("评论")
                .pathsToMatch(groupedApp("comment"), groupedApp("commentReplay"))
                .build();
    }

    @Bean
    public GroupedOpenApi home() {
        return GroupedOpenApi.builder()
                .group("平台信息")
                .pathsToMatch(grouped("homeAdvertise"), grouped("platformInformation"))
                .build();
    }

    @Bean
    public GroupedOpenApi member() {
        return GroupedOpenApi.builder()
                .group("会员")
                .pathsToMatch(groupedApp("member"), groupedApp("memberReceiveAddress"),
                        groupedApp("memberReadHistory"), groupedApp("memberCollection"),
                        groupedApp("memberReferralCode"))
                .build();
    }

    @Bean
    public GroupedOpenApi security() {
        return GroupedOpenApi.builder()
                .group("security")
                .pathsToMatch(grouped("auth"))
                .build();
    }

    @Bean
    public GroupedOpenApi order() {
        return GroupedOpenApi.builder()
                .group("订单")
                .pathsToMatch(groupedApp("order"), groupedApp("orderItem"),
                        groupedApp("orderReturnApply"), groupedApp("orderReturnReason"))
                .build();
    }

    @Bean
    public GroupedOpenApi product() {
        return GroupedOpenApi.builder()
                .group("商品")
                .pathsToMatch(grouped("productCategory"), grouped("product"))
                .build();
    }

    @Bean
    public GroupedOpenApi storage() {
        return GroupedOpenApi.builder()
                .group("文件存储")
                .pathsToMatch(grouped("storage"))
                .build();
    }
}