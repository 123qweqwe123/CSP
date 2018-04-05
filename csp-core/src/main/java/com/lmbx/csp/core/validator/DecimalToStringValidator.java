package com.lmbx.csp.core.validator;

import com.lmbx.csp.core.validator.annotation.DecimalToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Description:
 * <pre>
 *
 * </pre>
 *
 * @author javahuang
 * @since 2018/3/15 下午2:35
 */
public class DecimalToStringValidator implements ConstraintValidator<DecimalToString, Object> {

    private static Logger logger = LoggerFactory.getLogger(DecimalToStringValidator.class);

    private String[] includeFields;

    @Override
    public void initialize(DecimalToString constraintAnnotation) {
        this.includeFields = constraintAnnotation.includeFields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(value.getClass());
        for (PropertyDescriptor pd : pds) {
            try {
                Object fieldValue = pd.getReadMethod().invoke(value);
                String fieldName = pd.getName();
                if (Arrays.asList(includeFields).contains(fieldName)) {
                    if (fieldValue != null && fieldValue instanceof String) {
                        String stringValue = (String) fieldValue;
                        try {
                            DecimalFormat df = new DecimalFormat("#");
                            String formatValue = df.format(new BigDecimal(stringValue));
                            pd.getWriteMethod().invoke(value, formatValue);
                        } catch (Exception e) {
                            logger.warn("field {} value is not valid number, current value is {}", fieldName, stringValue);
                        }
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
