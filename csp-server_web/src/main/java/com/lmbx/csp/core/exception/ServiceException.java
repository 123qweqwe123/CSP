package com.lmbx.csp.core.exception;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * @author huangrupeng
 * @since 17/11/25 下午12:59
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
