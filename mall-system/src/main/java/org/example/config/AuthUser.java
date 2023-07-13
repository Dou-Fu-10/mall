package org.example.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Created by Dou-Fu-10 2023/7/9
 *
 * @author Dou-Fu-10
 * @date 2023/7/9
 * @Description 描述
 */
@Data
public class AuthUser {
    /**
     * 用户
     */
    @Schema(name = "username", description = "用户")
    private String username;
    /**
     * 密码
     */
    @Schema(name = "password", description = "密码")
    private String password;
}
