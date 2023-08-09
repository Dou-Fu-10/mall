package org.example.common.core.config;

import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.Data;
import org.example.common.core.exception.BaseRequestException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

/**
 * @author Dou-Fu-10
 * @date 2023/8/9
 * @Description 登录验证码配置信息
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "login-code")
public class CaptchaCodeConfig {
    /**
     * 验证码类型配置
     */
    private String codeType = "arithmetic";

    private int charType = Captcha.TYPE_DEFAULT;
    /**
     * 验证码有效期 分钟
     */
    private Long expiration = 2L;
    /**
     * 验证码内容长度
     */
    private int length = 2;
    /**
     * 验证码宽度
     */
    private int width = 111;
    /**
     * 验证码高度
     */
    private int height = 36;
    /**
     * 验证码字体
     */
    private String fontName = "楷体";
    /**
     * 字体大小
     */
    private int fontSize = 25;
    /**
     * 验证码
     */
    private Captcha captcha;

    public Captcha switchCaptcha() {
        Captcha captcha;
        synchronized (this) {
            switch (codeType) {
//                    case "arithmetic" -> {
//                        // 算术类型
//                        captcha = new ArithmeticCaptcha(width, height);
//                        // 几位数运算，默认是两位
//                        captcha.setLen(length);
//                    }
                case "chinese" -> {
                    // 中文类型
                    captcha = new ChineseCaptcha(width, height);
                    captcha.setLen(length);
                }
                case "chinese_gif" -> {
                    // 中文gif类型
                    captcha = new ChineseGifCaptcha(width, height);
                    captcha.setLen(length);
                }
                case "gif" -> {
                    // gif类型
                    captcha = new GifCaptcha(width, height);
                    captcha.setLen(length);
                }
                case "spec" -> {
                    // png类型
                    captcha = new SpecCaptcha(width, height);
                    captcha.setLen(length);
                }
                default -> throw new BaseRequestException("验证码配置信息错误!");
            }
        }

        try {
            captcha.setFont(Captcha.FONT_1);
        } catch (Exception o) {
            captcha.setFont(new Font(fontName, Font.PLAIN, fontSize));
        }

        // charType 值要在访问内
        if (charType >= 1 && charType <= 6) {
            captcha.setCharType(charType);
        } else {
            // 使用默认值
            captcha.setCharType(Captcha.TYPE_DEFAULT);
        }
        return captcha;
    }
}
