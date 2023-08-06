package org.example.common.core.aspect;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.common.core.validation.PasswordValid;

import java.util.regex.Pattern;

/**
 * 验证 密码强度
 *
 * @author IKUN
 */
public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {

    private static final Pattern NUMBER_PATTERN = Pattern.compile(".*\\d+.*");
    private static final Pattern LETTER_PATTERN = Pattern.compile(".*[a-zA-Z]+.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[^a-zA-Z0-9]+.*");

    private boolean needNumber;
    private boolean needLetter;
    private boolean needSpecialChar;
    private int min;
    private int max;
    private boolean isNotBlank;

    @Override
    public void initialize(PasswordValid constraintAnnotation) {
        this.needNumber = constraintAnnotation.number();
        this.needLetter = constraintAnnotation.letter();
        this.needSpecialChar = constraintAnnotation.specialChar();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.isNotBlank = constraintAnnotation.isNotBlank();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 提交的必须是空白 同时要支持提交空白
        if (StringUtils.isBlank(value) && !isNotBlank) {
            return true;
        }
        if (StringUtils.isBlank(value)) {
            return false;
        }
        String password = value;
        // 密码解密
//        try {
//            password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, value);
//        } catch (Exception e) {
//            return false;
//        }
        if (StringUtils.isBlank(password)) {
            return false;
        }
        if (password.length() < min && password.length() > max) {
            return false;
        }

        if (needNumber && !NUMBER_PATTERN.matcher(password).matches()) {
            return false;
        }

        if (needLetter && !LETTER_PATTERN.matcher(password).matches()) {
            return false;
        }

        return !needSpecialChar || SPECIAL_CHAR_PATTERN.matcher(password).matches();
    }
}