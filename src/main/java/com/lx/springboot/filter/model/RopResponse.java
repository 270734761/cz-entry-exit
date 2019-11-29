package com.lx.springboot.filter.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.beans.Transient;
import java.io.Serializable;

/**
 * Created by zhangjun on 18/8/28.
 */
public class RopResponse<T> implements Serializable {
	private static final long serialVersionUID = -5361663101292847563L;
	
	String code = "1";
    String message = "";
    String errorMessage = "";
    String debugMsg;

    //接口耗时
    private Long costTime;

    /**
     * O2O使用signature字段
     * ROUTE使用version字段
     */
    @JSONField(name = "version")
    String signature; //返回数据签名

    private T data;
    
    public RopResponse() {
    	
    }
    
    public RopResponse(T data, String code, String message, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.message = message;
        this.code = code;
    }

    public static <T> RopResponse<T> success(T data) {
        return new RopResponse<T>(data, "1", null, null);
    }

    public static <T> RopResponse<T> success(T data, String message) {
        return new RopResponse<T>(data, "1", message, null);
    }

    public static <T> RopResponse<T> success(String code, T data, String message, String errorMessage) {
        return new RopResponse<T>(data, code, message, errorMessage);
    }

    public static <T> RopResponse<T> error(String code, String errorMessage) {
        return new RopResponse<T>(null, code, errorMessage, errorMessage);
    }

    public static <T> RopResponse<T> error(String code, String message, String errorMessage) {
        return new RopResponse<T>(null, code, message, errorMessage);
    }

    @Transient
    public boolean isSuccess() {
        return "1".equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDebugMsg() {
        return debugMsg;
    }

    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

}
