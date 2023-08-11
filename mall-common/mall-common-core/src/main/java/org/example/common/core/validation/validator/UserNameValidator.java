package org.example.common.core.validation.validator;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.common.core.validation.UserNameValid;

import java.util.regex.Pattern;

/**
 * Created by Dou-Fu-10 2023/8/10
 *
 * @author Dou-Fu-10
 * @date 2023/8/10
 * @Description 验证 密码强度
 */
public class UserNameValidator implements ConstraintValidator<UserNameValid, String> {
    // 用于判断字符串中是否包含数字。
    private static final Pattern NUMBER_PATTERN = Pattern.compile(".*\\d+.*");
    // 用于判断字符串中是否包含字母。
    private static final Pattern LETTER_PATTERN = Pattern.compile(".*[a-zA-Z]+.*");
    // 用于判断字符串中是否包含非字母和非数字的特殊字符。
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[^a-zA-Z0-9]+.*");
    // 是否需要包含数字，默认需要
    private boolean needNumber;
    // 是否需要包含字母，默认需要
    private boolean needLetter;
    // 是否需要包含特殊字符，默认需要
    private boolean needSpecialChar;
    // 最小长度，默认8位
    private int min;
    // 最大长度，默认16位
    private int max;
    // 允许为空  默认不允许为空
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
        // 校验是否为空
        if (StringUtils.isBlank(value)) {
            return false;
        }
        // 长度校验
        if (value.length() < min || value.length() > max) {
            return false;
        }
        // 是否需要包含数字，默认需要    用于判断字符串中是否包含数字。
        if (needNumber && !NUMBER_PATTERN.matcher(value).matches()) {
            return false;
        }
        // 是否需要包含字母，默认需要      用于判断字符串中是否包含字母。
        if (needLetter && !LETTER_PATTERN.matcher(value).matches()) {
            return false;
        }
        // 是否需要包含特殊字符，默认需要   用于判断字符串中是否包含非字母和非数字的特殊字符。
        return !needSpecialChar || SPECIAL_CHAR_PATTERN.matcher(value).matches();
    }
}