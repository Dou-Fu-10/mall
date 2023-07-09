package org.example.security.config;

import lombok.RequiredArgsConstructor;
import org.example.security.annotaion.AnonymousAccess;
import org.example.security.enums.RequestMethodEnum;
import org.example.security.security.JwtAccessDeniedHandler;
import org.example.security.security.JwtAuthenticationEntryPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * @author Zheng Jie
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalAuthentication
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final ApplicationContext applicationContext;


    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 禁用 CSRF
                .csrf(csrfCustomizer())
                // 添加过滤器
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                // 异常处理定制器
                .exceptionHandling(exceptionHandlingCustomizer())
                // 防止 iframe 造成跨域
                .headers(headersCustomizer())
                // 会话管理定制器
                .sessionManagement(sessionManagementCustomizer())
                // 授权Http请求定制器
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer());
        return httpSecurity.build();
    }

    private Map<String, Set<String>> getAnonymousUrl(Map<RequestMappingInfo, HandlerMethod> handlerMethodMap) {
        Map<String, Set<String>> anonymousUrls = new HashMap<>(6);
        Set<String> get = new HashSet<>();
        Set<String> post = new HashSet<>();
        Set<String> put = new HashSet<>();
        Set<String> patch = new HashSet<>();
        Set<String> delete = new HashSet<>();
        Set<String> all = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
            if (null != anonymousAccess) {
                List<RequestMethod> requestMethods = new ArrayList<>(infoEntry.getKey().getMethodsCondition().getMethods());
                RequestMethodEnum request = RequestMethodEnum.find(requestMethods.size() == 0 ? RequestMethodEnum.ALL.getType() : requestMethods.get(0).name());
                switch (Objects.requireNonNull(request)) {
                    case GET:
                        get.addAll(infoEntry.getKey().getPatternValues());
                        break;
                    case POST:
                        post.addAll(infoEntry.getKey().getPatternValues());
                        break;
                    case PUT:
                        put.addAll(infoEntry.getKey().getPatternValues());
                        break;
                    case PATCH:
                        patch.addAll(infoEntry.getKey().getPatternValues());
                        break;
                    case DELETE:
                        delete.addAll(infoEntry.getKey().getPatternValues());
                        break;
                    default:
                        all.addAll(infoEntry.getKey().getPatternValues());
                        break;
                }
            }
        }
        anonymousUrls.put(RequestMethodEnum.GET.getType(), get);
        anonymousUrls.put(RequestMethodEnum.POST.getType(), post);
        anonymousUrls.put(RequestMethodEnum.PUT.getType(), put);
        anonymousUrls.put(RequestMethodEnum.PATCH.getType(), patch);
        anonymousUrls.put(RequestMethodEnum.DELETE.getType(), delete);
        anonymousUrls.put(RequestMethodEnum.ALL.getType(), all);
        return anonymousUrls;
    }

    @SuppressWarnings("all")
    private Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer() {
        // 在这里进行CSRF相关的自定义配置
        return (csrf) -> {
            // 添加自定义的CSRF配置
            csrf.disable(); // 禁用CSRF保护
            // 其他配置...
        };
    }

    private Customizer<ExceptionHandlingConfigurer<HttpSecurity>> exceptionHandlingCustomizer() {
        // 在这里进行异常处理的自定义配置
        return (exceptionHandling) -> {
            // 添加自定义的异常处理配置
            exceptionHandling
                    .accessDeniedHandler(jwtAccessDeniedHandler) // 拒绝访问处理程序
                    .authenticationEntryPoint(authenticationErrorHandler); // 配置自定义的认证入口点
        };
    }

    @SuppressWarnings("all")
    private Customizer<HeadersConfigurer<HttpSecurity>.FrameOptionsConfig> frameOptionsCustomizer() {
        // 在这里进行X-Frame-Options的自定义配置
        return (frameOptions) -> {
            // 添加自定义的X-Frame-Options配置
            frameOptions
                    .disable(); // 设置X-Frame-Options为SAMEORIGIN
        };
    }

    private Customizer<HeadersConfigurer<HttpSecurity>> headersCustomizer() {
        return (headers) -> {
            // 配置其他的响应头选项
            headers
                    .frameOptions(frameOptionsCustomizer()); // 配置X-Frame-Options
        };
    }

    private Customizer<SessionManagementConfigurer<HttpSecurity>> sessionManagementCustomizer() {
        // 在这里进行会话管理的自定义配置
        return (sessionManagement) -> {
            // 添加自定义的会话管理配置
            sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 设置会话创建策略为无状态
//                    .maximumSessions(1) // 设置最大允许的会话数为1
//                    .maxSessionsPreventsLogin(true); // 设置超过最大会话数时阻止登录
            ;
        };
    }

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authorizeHttpRequestsCustomizer() {
        // 搜寻匿名标记 url： @AnonymousAccess
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
        // 获取匿名标记
        Map<String, Set<String>> anonymousUrls = getAnonymousUrl(handlerMethodMap);

        // 在这里进行请求授权的自定义配置
        return (authorizeRequests) -> {
            // 添加自定义的请求授权配置
            authorizeRequests
                    // 静态资源等等
                    .requestMatchers(
                            HttpMethod.GET,
                            "/*.html",
                            "/*/*.html",
                            "/*/*.css",
                            "/*/*.js",
                            "/webSocket/**"
                    ).permitAll()
                    // swagger 文档
                    .requestMatchers("/swagger-ui.html").permitAll()
                    .requestMatchers("/swagger-resources/**").permitAll()
                    .requestMatchers("/webjars/**").permitAll()
                    .requestMatchers("/*/api-docs").permitAll()
                    // 文件
                    .requestMatchers("/avatar/**").permitAll()
                    .requestMatchers("/file/**").permitAll()
                    // 阿里巴巴 druid
                    .requestMatchers("/druid/**").permitAll()
                    // 放行OPTIONS请求
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    // 自定义匿名访问所有url放行：允许匿名和带Token访问，细腻化到每个 Request 类型
                    // GET
                    .requestMatchers(HttpMethod.GET, anonymousUrls.get(RequestMethodEnum.GET.getType()).toArray(new String[0])).permitAll()
                    // POST
                    .requestMatchers(HttpMethod.POST, anonymousUrls.get(RequestMethodEnum.POST.getType()).toArray(new String[0])).permitAll()
                    // PUT
                    .requestMatchers(HttpMethod.PUT, anonymousUrls.get(RequestMethodEnum.PUT.getType()).toArray(new String[0])).permitAll()
                    // PATCH
                    .requestMatchers(HttpMethod.PATCH, anonymousUrls.get(RequestMethodEnum.PATCH.getType()).toArray(new String[0])).permitAll()
                    // DELETE
                    .requestMatchers(HttpMethod.DELETE, anonymousUrls.get(RequestMethodEnum.DELETE.getType()).toArray(new String[0])).permitAll()
                    // 所有类型的接口都放行
                    .requestMatchers(anonymousUrls.get(RequestMethodEnum.ALL.getType()).toArray(new String[0])).permitAll()
                    // 所有请求都需要认证
                    .anyRequest().authenticated(); // 其他所有路径禁止访问
        };
    }
}