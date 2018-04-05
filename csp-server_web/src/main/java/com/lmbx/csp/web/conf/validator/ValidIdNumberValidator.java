package com.lmbx.csp.web.conf.validator;

import com.lmbx.csp.core.utils.IdcardUtil;
import com.lmbx.csp.web.conf.vo.UploadPersonVO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Description:
 * 
 * <pre>
 * 身份证号校验器
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/5 上午8:57
 */
public class ValidIdNumberValidator implements ConstraintValidator<ValidIdNumber, UploadPersonVO> {

    @Override
    public void initialize(ValidIdNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(UploadPersonVO value, ConstraintValidatorContext context) {
        if (value.getIdTypeStr() != null && "身份证".equals(value.getIdTypeStr())
            && !IdcardUtil.isIdcard(value.getIdNumber())) {
            return false;
        }
        return true;
    }
}
