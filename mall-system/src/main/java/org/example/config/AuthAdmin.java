package org.example.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.common.core.validation.UserNameValid;

/**
 * Created by Dou-Fu-10 2023/7/9
 *
 * @author Dou-Fu-10
 * @date 2023/7/9
 * @Description 描述
 */
@Data
public class AuthAdmin {
    /**
     * 用户
     */
    @Schema(name = "username", description = "用户")
    @UserNameValid
    private String username;
    /**
     * 密码
     */
    @Schema(name = "password", description = "密码")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", message = "密码必须由8到16个字符组成，包含至少一个小写字母、一个大写字母和一个数字。")
    private String password;
}
