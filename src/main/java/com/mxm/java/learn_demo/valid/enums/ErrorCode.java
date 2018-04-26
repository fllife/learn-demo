package com.mxm.java.learn_demo.valid.enums;

/**
 * Create by maxianming 2018/4/26 16:53
 */
public enum ErrorCode {
    INVALIAD_PARAM("1001","参数不合法"),
    UNKONWN_ERROR("1002","系统异常"),
    ;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误描述
     */
    private String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
