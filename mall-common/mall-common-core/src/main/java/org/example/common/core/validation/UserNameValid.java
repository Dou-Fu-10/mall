package org.example.common.core.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.common.core.aspect.PasswordValidator;

import java.lang.annotation.*;

/**
 * 验证密码强度
 *
 * @author IKUN
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface UserNameValid {
    // 最小长度，默认8位
    int min() default 6;

    // 最大长度，默认16位
    int max() default 16;

    // 是否需要包含数字，默认需要
    boolean number() default true;

    // 是否需要包含字母，默认需要
    boolean letter() default true;

    // 是否需要包含特殊字符，默认需要
    boolean specialChar() default false;

    String message() default "账号不符合要求";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // 默认不能提交空白
    boolean isNotBlank() default true;


    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        UserNameValid[] value();
    }

}