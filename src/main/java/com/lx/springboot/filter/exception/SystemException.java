package com.lx.springboot.filter.exception;


import com.lx.springboot.filter.contants.SystemErrorCode;
import com.lx.springboot.filter.model.RopCode;

/**
 * Created by zhangjun on 19/8/28.
 */
public class SystemException extends LvtuException {

    private SystemErrorCode systemErrorCode= SystemErrorCode.unexpected_error;;

    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(SystemErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.systemErrorCode = errorCode;
    }

    public SystemException(SystemErrorCode errorCode, String message) {
        super(message);
        this.systemErrorCode = errorCode;
    }

    public SystemException(SystemErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.systemErrorCode = errorCode;
    }

    public SystemErrorCode getRouteErrorCode() {
        return systemErrorCode;
    }

    @Override
    public RopCode getErrorCode() {
        return systemErrorCode.getErrorCode();
    }

    @Override
    public String getFriendMessage() {
        return systemErrorCode.getErrorMessage();
    }
}
