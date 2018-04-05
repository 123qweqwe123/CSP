package com.lmbx.csp.core.validator.annotation;

import com.lmbx.csp.core.validator.DecimalToStringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2018/3/15 下午2:34
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { DecimalToStringValidator.class })
@Documented
public @interface DecimalToString{

    String message() default "";

    /**
     * 是否去掉空格
     *
     * @return
     */
    boolean trim() default true;

    /**
     * 需要处理的字段
     *
     * @return
     */
    String[] includeFields() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
