package com.lx.springboot.Enums;

/**
 * @Author zhangjun
 * @Date 2019/11/25
 */
public enum ApplyTypeEnum {
    PASSPORT("passport", "护照"),
    TAIWANPASS("taiwanPass", "往来台湾通行证"),
    GANGAOPASS("gangaoPass", "往来港澳通行证"),

    ;

    private String modelType;
    private String desc;

    ApplyTypeEnum(String modelType, String desc) {
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
        for (ApplyTypeEnum value : ApplyTypeEnum.values()) {
            if(value.getModelType().equals(type)){
                return value.getDesc();
            }
        }
        return "";
    }

}
