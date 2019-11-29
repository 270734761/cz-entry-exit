package com.lx.springboot.filter.model;

/**
 * Created by zhangjun on 19/8/28.
 */
public enum RopCode {
    WARNING("2"),//警告，客户端对该错误码做特殊弹窗处理
    OK("1"),//提示Message
    ERROR("-1"),//提示errorMessage
    NO_PRODUCT("-2"),//无产品或者产品不可售
    UNSUPPORT_PRODUCT_VERSION("-2"),//当前版本不支持
    TO_LOGIN("-3"),
    UN_PERMISSION("-7"),//无权限
    NEED_VALIDATE_CODE("-5"),//验证参数失败
	IMAGE_CODE_ERROR("-101"),//图片验证码失败
	RISK_SMS("-9"),// 风控短信验证
	RISK_BLOCK("-10"),// 风控阻断
	NEED_GEETEST_CODE("3"),//跳转到极验页面
    COUPON_BIND_FAIL("-18"),
    TO_BINDING_MOBILE("-6");//跳转到绑定手机号页面

    private String code;

    RopCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name();
    }

}
