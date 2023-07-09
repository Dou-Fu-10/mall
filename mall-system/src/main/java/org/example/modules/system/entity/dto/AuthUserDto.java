package org.example.modules.system.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author IKUN
 * @since 2023-05-31 21:25:43
 */
@Data
public class AuthUserDto {

    /**
     * 账号
     */
    @NotBlank
    private String username;
    /**
     * 密码
     */
    @NotBlank
    private String password;
}
