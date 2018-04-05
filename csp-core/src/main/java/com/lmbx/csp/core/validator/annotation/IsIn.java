package com.lmbx.csp.core.validator.annotation;

import com.lmbx.csp.core.validator.IsInValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description:
 * 
 * <pre>
 *     检查 bean 的 field 值是否在 values 里面
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/4 下午7:01
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = IsInValidator.class)
@Documented
public @interface IsIn {

    String message();

    String[] values();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
