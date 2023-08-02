package org.example.config;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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
    @NotEmpty
    @Schema(name = "用户名")
    private String phone;
    @NotEmpty
    @Schema(name = "旧密码")
    private String oldPassword;
    @NotEmpty
    @Schema(name = "新密码")
    private String newPassword;
}