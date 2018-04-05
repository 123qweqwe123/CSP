package com.lmbx.csp.core.mvc.formatter;

import java.lang.annotation.*;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * @author javahuang
 * @since : 17/9/19 下午6:34
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
public @interface MyDateTimeFormat {

}
