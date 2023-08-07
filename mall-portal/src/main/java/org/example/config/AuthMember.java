package org.example.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.common.core.validation.PasswordValid;
import org.example.common.core.validation.PhoneValid;

/**
 * Created by Dou-Fu-10 2023/8/1
 *
 * @author Dou-Fu-10
 * @date 2023/8/1
 * @Description 描述
 */
@Data
public class AuthMember {
    /**
     * 用户
     */
    @Schema(name = "username", description = "用户")
//    @PhoneValid
    private String phone;
    /**
     * 密码
     */
    @Schema(name = "password", description = "密码")
//    @PasswordValid
    private String password;
    /**
     * 验证码
     */
    @Schema(name = "code", description = "验证码")
    private String code;
}
