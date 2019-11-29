package com.lx.springboot.filter.contants;


import com.lx.springboot.filter.model.RopCode;
import com.lx.springboot.filter.model.RopResponse;

/**
 * Created by zhangjun on 19/8/28.
 */
public enum SystemErrorCode {
	/*distributed_lock_failed(RopCode.ERROR, "小驴正在处理中"),
    permission_error(RopCode.TO_LOGIN, "权限不满足"),
    unexpected_error(RopCode.ERROR, "小驴找不到妈妈了~"),
    md5_check_failure(RopCode.ERROR, "签名校验失败"),
    xss_inject_error(RopCode.ERROR, "抱歉，您输入的信息有敏感信息，请您修改后重新输入！"),
    sql_inject_error(RopCode.ERROR, "抱歉，您输入的信息有敏感信息，请您修改后重新输入！"),
    unpermission_error(RopCode.UN_PERMISSION, "当前登录角色无权限访问此接口~"),
    too_many_access(RopCode.ERROR, "您访问太过频繁了，请稍后再试"),
	user_has_blocked(RopCode.ERROR, "该账号已被冻结，无法使用"),
    user_has_disabled(RopCode.ERROR,"该账号已被注销，无法使用"),
	user_inactived(RopCode.ERROR, "账号待激活，请通过电脑登录进行账号激活，恢复账号使用!"),*/

    unexpected_error(RopCode.ERROR, "小驴找不到妈妈了~"),
    session_id_null(RopCode.ERROR, "sessionId为空!"),
    auth_illegal(RopCode.TO_LOGIN, "未登录授权"),
    member_illegal(RopCode.ERROR, "会员不合法!");

    private final RopCode errorCode;
    private final String errorMessage;


    SystemErrorCode(RopCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public RopCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public <T> RopResponse<T> create() {
        return RopResponse.error(getErrorCode().getCode(), getErrorMessage(), null);
    }

    public <T> RopResponse<T> create(String extraMessage) {
        return RopResponse.error(getErrorCode().getCode(), getErrorMessage(), extraMessage);
    }
}
