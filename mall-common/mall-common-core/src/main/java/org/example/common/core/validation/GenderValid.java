package org.example.common.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.common.core.validation.validator.GenderValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Repeatable(GenderValid.List.class)
public @interface GenderValid {
    // 错误提示信息
    String message() default "性别填写错误,可填写值'男'或'女'";

    Class<?>[] groups() default {};

    // 允许为空  默认不允许为空
    boolean allowNull() default false;

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        GenderValid[] value();
    }
}