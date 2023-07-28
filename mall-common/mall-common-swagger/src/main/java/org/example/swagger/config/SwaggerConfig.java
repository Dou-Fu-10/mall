package org.example.swagger.config;

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
        info.title("商城系统");
        info.description("电商商城系统");
        info.version("1.0");
        info.contact(new Contact().name("Dou-Fu-10").url("https://github.com/Dou-Fu-10"));
        info.license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"));
        return new OpenAPI().info(info);
    }


    private String groupedApi(String api) {
        return "/api/" + api + "/**";
    }

    private String grouped(String api) {
        return "/" + api + "/**";
    }

    @Bean
    public GroupedOpenApi portal() {
        return GroupedOpenApi.builder()
                .group("前台")
                .pathsToMatch(groupedApi("product"))
                .build();
    }

    @Bean
    public GroupedOpenApi comment() {
        return GroupedOpenApi.builder()
                .group("商品评论")
                .pathsToMatch(groupedApi("commentReplay"), groupedApi("comment"))
                .build();
    }

    @Bean
    public GroupedOpenApi member() {
        return GroupedOpenApi.builder()
                .group("会员")
                .pathsToMatch(grouped("member"), grouped("memberLevel"),
                        grouped("memberLoginLog"), grouped("memberPrice"),
                        grouped("memberReceiveAddress"))
                .build();
    }

    @Bean
    public GroupedOpenApi order() {
        return GroupedOpenApi.builder()
                .group("订单")
                .pathsToMatch(groupedApi("order"), groupedApi("orderItem"),
                        groupedApi("orderOperateHistory"), groupedApi("orderReturnApply"),
                        groupedApi("orderReturnReason"), groupedApi("orderSetting"))
                .build();
    }

    @Bean
    public GroupedOpenApi product() {
        return GroupedOpenApi.builder()
                .group("商品")
                .pathsToMatch(groupedApi("productAttributeCategory"), groupedApi("productAttribute"),
                        groupedApi("productAttributeValue"), groupedApi("productCategoryAttributeRelation"),
                        groupedApi("productCategory"), groupedApi("productFullReduction"),
                        groupedApi("productLadder"), groupedApi("productOperateLog"),
                        groupedApi("productVertifyRecord"), groupedApi("skuStock"), groupedApi("product"))
                .build();
    }

    @Bean
    public GroupedOpenApi admin() {
        return GroupedOpenApi.builder()
                .group("管理员")
                .pathsToMatch(groupedApi("admin"), groupedApi("adminLoginLog"),
                        groupedApi("adminRole"), groupedApi("menu"),
                        groupedApi("role"), groupedApi("rolesMenus"),
                        groupedApi("companyAddress"), groupedApi("feightTemplate"),
                        groupedApi("homeAdvertise"), "/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi storage() {
        return GroupedOpenApi.builder()
                .group("文件存储")
                .pathsToMatch(groupedApi("storage"))
                .build();
    }
}