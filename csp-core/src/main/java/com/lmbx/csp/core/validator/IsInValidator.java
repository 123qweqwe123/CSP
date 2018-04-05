package com.lmbx.csp.core.validator;

import com.lmbx.csp.core.validator.annotation.IsIn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/4 下午7:03
 */
public class IsInValidator implements ConstraintValidator<IsIn, String> {

    String[] values;

    @Override
    public void initialize(IsIn constraintAnnotation) {
        values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.asList(values).contains(value);
    }
}
