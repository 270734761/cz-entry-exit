package com.lx.springboot.query;

import java.io.Serializable;

public class ZolozIdentificationQuery implements Serializable {

    private static final long serialVersionUID = -6062160134979070657L;

    private String bizId;//商户请求的唯一标识，须与初始化传入的bizId保持一致

    private String   zimId ;// 刷脸认证的唯一标识，用于查询认证结果

    private String   externParam ;//扩展参数

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getZimId() {
        return zimId;
    }

    public void setZimId(String zimId) {
        this.zimId = zimId;
    }

    public String getExternParam() {
        return externParam;
    }

    public void setExternParam(String externParam) {
        this.externParam = externParam;
    }

    @Override
    public String toString() {
        return "ZolozIdentificationQuery{" +
                "bizId='" + bizId + '\'' +
                ", zimId='" + zimId + '\'' +
                ", externParam='" + externParam + '\'' +
                '}';
    }
}
