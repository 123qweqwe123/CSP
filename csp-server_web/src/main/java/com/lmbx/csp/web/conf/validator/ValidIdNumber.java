package com.lmbx.csp.web.conf.validator;

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
 *     validator 校验证件号
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/5 上午8:56
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidIdNumberValidator.class })
@Documented
public @interface ValidIdNumber {

    String message() default "身份证号不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
