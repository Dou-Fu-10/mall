package org.example.common.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.common.core.validation.validator.PhoneValidator;

import java.lang.annotation.*;

/**
 * 验证手机号，空和正确的手机号都能验证通过<br/>
 * 正确的手机号由11位数字组成，第一位为1
 * 第二位为 3、4、5、7、8
 *
 * @author PC
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Repeatable(PhoneValid.List.class)
public @interface PhoneValid {
    String message() default "手机号校验错误";

    Class<?>[] groups() default {};

    // 默认不允许为空
    boolean allowNull() default false;

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        PhoneValid[] value();
    }
}