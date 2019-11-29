package com.lx.springboot.filter.exception;



import com.lx.springboot.filter.model.RopCode;

import java.io.IOException;

/**
 * 统一异常基类
 * Created by zhangjun on 19/8/28.
 */
public abstract class LvtuException extends IOException {

    public LvtuException() {
    }

    public LvtuException(String message) {
        super(message);
    }

    public LvtuException(String message, Throwable cause) {
        super(message, cause);
    }

    public LvtuException(Throwable cause) {
        super(cause);
    }

  /*  public LvtuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }*/

    public abstract RopCode getErrorCode();

    public abstract String getFriendMessage();
}
