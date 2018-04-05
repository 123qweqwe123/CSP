package com.lmbx.csp.core.validator.annotation;

import com.lmbx.csp.core.validator.TrimSpaceValidator;

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
 * 
 * <pre>
     去掉对象字段里面的空格（英文名称只去除首尾空格）
 * </pre>
 *
 * @author javahuang
 * @since 2018/1/5 上午9:35
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { TrimSpaceValidator.class })
@Documented
public @interface TrimSpace {

    String message() default "";

    /**
     * 是否去掉空格
     * 
     * @return
     */
    boolean trim() default true;

    /**
     * 不去除某些字段
     * 
     * @return
     */
    String[] excludeFields() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
