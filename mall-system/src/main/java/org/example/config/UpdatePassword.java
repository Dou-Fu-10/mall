package org.example.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.common.core.validation.PasswordValid;
import org.example.common.core.validation.UserNameValid;

/**
 * Created by Dou-Fu-10 2023-07-11
 *
 * @author Dou-Fu-10
 * @date 2023-07-11
 * @Description 修改密码实体类
 */
@Schema(name = "修改密码实体类", description = "修改密码实体类")
@Data
public class UpdatePassword {

    @Schema(name = "用户名")
    @UserNameValid
    private String username;

    @Schema(name = "旧密码")
    @PasswordValid
    private String oldPassword;

    @Schema(name = "新密码")
    @PasswordValid
    private String newPassword;
}
