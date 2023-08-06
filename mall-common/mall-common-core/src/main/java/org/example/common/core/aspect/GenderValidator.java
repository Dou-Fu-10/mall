package org.example.common.core.aspect;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.common.core.utils.StringUtils;
import org.example.common.core.validation.GenderValid;

public class GenderValidator implements ConstraintValidator<GenderValid, String> {

    private boolean allowNull;

    @Override
    public void initialize(GenderValid constraintAnnotation) {
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
            return "男".equals(value) || "女".equals(value);
        }
    }
}