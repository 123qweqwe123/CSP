package com.lmbx.csp.core.exception;

/**
 * Description:
 * 
 * <pre>
 * 表单校验异常
 * </pre>
 * 
 * @author huangrupeng
 * @since 17/11/25 下午12:59
 */
public class ValidatorException extends RuntimeException {

    public ValidatorException(){
    }

    public ValidatorException(String message){
        super(message);
    }

    public ValidatorException(String message, Throwable cause){
        super(message, cause);
    }

    public ValidatorException(Throwable cause){
        super(cause);
    }
}
