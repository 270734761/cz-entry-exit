package com.lx.springboot.Enums;

/**
 * @Author zhangjun
 * @Date 2019/11/25
 */
public enum FlowStateEnum {
    SUBMITTED("submitted", "已提交"),
    ACCEPTED("accepted", "已受理"),
    COMPLETED("completed", "已完成"),

    ;

    private String modelType;
    private String desc;

    FlowStateEnum(String modelType, String desc) {
        this.modelType = modelType;
        this.desc = desc;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDescByType(String type){
        for (FlowStateEnum value : FlowStateEnum.values()) {
            if(value.getModelType().equals(type)){
                return value.getDesc();
            }
        }
        return "";
    }
}
