package org.example.common.core.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.common.core.utils.StringUtils;
import org.example.common.core.validation.PhoneValid;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<PhoneValid, String> {

    private static final Pattern China_Eleven_PATTERN = Pattern.compile("^$|((13[0-9])|(14[05679])|(15([0-3,5-9]))|(16[2567])|(17[01235678])|(18[0-9]|19[135689]))\\d{8}$");

    private boolean allowNull;

    @Override
    public void initialize(PhoneValid constraintAnnotation) {
        this.allowNull = constraintAnnotation.allowNull();
    }

    /**
     * 如果匹配返回true 不匹配返回false
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value) && allowNull) {
            return true;
        }
        if (StringUtils.isEmpty(value)) {
            return false;
        } else {
            return China_Eleven_PATTERN.matcher(value).matches();
        }
    }
}