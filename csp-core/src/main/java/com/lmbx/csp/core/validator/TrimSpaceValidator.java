package com.lmbx.csp.core.validator;

import com.lmbx.csp.core.validator.annotation.TrimSpace;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Description:
 * 
 * <pre>
 * 去掉对象字段里面的空格
 * </pre>
 *
 * @author javahuang
 * @since 2018/1/5 上午9:46
 */
public class TrimSpaceValidator implements ConstraintValidator<TrimSpace, Object> {

    private boolean  trimSpace;
    private String[] excludeFields;

    @Override
    public void initialize(TrimSpace constraintAnnotation) {
        this.trimSpace = constraintAnnotation.trim();
        this.excludeFields = constraintAnnotation.excludeFields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean nonSpace = true;
        if (trimSpace) {
            PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(value.getClass());
            for (PropertyDescriptor pd : pds) {
                try {
                    Object fieldValue = pd.getReadMethod().invoke(value);
                    // 只对 string 进行处理
                    if (fieldValue != null && fieldValue instanceof String) {
                        String stringValue = (String) fieldValue;
                        // 去除首尾的空格
                        stringValue = StringUtils.trimLeadingWhitespace(stringValue);
                        stringValue = StringUtils.trimTrailingWhitespace(stringValue);
                        // 非英文名称去掉字符串中间的空格
                        if (!isEnglish(stringValue)) {
                            stringValue = StringUtils.trimAllWhitespace(stringValue);
                        }
                        pd.getWriteMethod().invoke(value, stringValue);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return nonSpace;
    }

    /**
     * 判断是否是英文姓名
     * 
     * @param source
     * @return
     */
    public boolean isEnglish(String source) {
        source = source.replaceAll("\\s+", "");
        return source.matches("[A-Za-z]+");
    }
}
