package org.example.common.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.common.core.aspect.NickNameValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NickNameValidator.class)
@Repeatable(NickNameValid.List.class)
public @interface NickNameValid {

    // 错误提示信息
    String message() default "昵称输入错误";

    Class<?>[] groups() default {};

    // 允许为空  默认不允许为空
    boolean allowNull() default false;

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        NickNameValid[] value();
    }
}
