package org.example.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Dou-Fu-10
 * @date 2023-07-07
 * @Description 权限校验
 */
@Slf4j
@Service(value = "hasPermission")
public class MallPermission {
    public Boolean check(String... permissions) {
        log.info(Arrays.toString(permissions));
        return true;
//        // 获取当前用户的所有权限
//        List<String> elPermissions = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
//        // 判断当前用户的所有权限是否包含接口上定义的权限
//        return elPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
    }
}
