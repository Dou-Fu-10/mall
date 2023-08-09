package org.example.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    @Schema(name = "phone", description = "用户")
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
    @Schema(name = "SMSVerificationCode", description = "短信验证码")
    private Integer SMSVerificationCode;
    /**
     * 验证码
     */
    @Schema(name = "imageCaptcha", description = "图片验证码")
    private String imageCaptcha;
    /**
     * 校验图片验证码
     */
    @Schema(name = "uuid", description = "校验图片验证码")
    private String uuid;
    /**
     * 验证码
     */
    @Schema(name = "referralCode", description = "推荐码")
    private String referralCode;
}
