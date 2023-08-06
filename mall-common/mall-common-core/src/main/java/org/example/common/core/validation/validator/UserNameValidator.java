package org.example.common.core.validation.validator;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.common.core.validation.PasswordValid;
import org.example.common.core.validation.UserNameValid;

import java.util.regex.Pattern;

/**
 * 验证 密码强度
 *
 * @author IKUN
 */
public class UserNameValidator implements ConstraintValidator<UserNameValid, String> {

    private static final Pattern NUMBER_PATTERN = Pattern.compile(".*\\d+.*");
    private static final Pattern LETTER_PATTERN = Pattern.compile(".*[a-zA-Z]+.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[^a-zA-Z0-9]+.*");

    private boolean needNumber;
    private boolean needLetter;
    private boolean needSpecialChar;
    private int min;
    private int max;
    private boolean allowNull;

    @Override
    public void initialize(UserNameValid constraintAnnotation) {
        this.needNumber = constraintAnnotation.number();
        this.needLetter = constraintAnnotation.letter();
        this.needSpecialChar = constraintAnnotation.specialChar();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 提交的必须是空白 同时要支持提交空白
        if (StringUtils.isBlank(value) && allowNull) {
            return true;
        }
        if (StringUtils.isBlank(value)) {
            return false;
        }
        if (value.length() < min && value.length() > max) {
            return false;
        }

        if (needNumber && !NUMBER_PATTERN.matcher(value).matches()) {
            return false;
        }

        if (needLetter && !LETTER_PATTERN.matcher(value).matches()) {
            return false;
        }

        return !needSpecialChar || SPECIAL_CHAR_PATTERN.matcher(value).matches();
    }
}