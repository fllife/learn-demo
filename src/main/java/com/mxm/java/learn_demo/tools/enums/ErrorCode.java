package com.mxm.java.learn_demo.tools.enums;

/**
 * Create by maxianming 2018/4/26 16:53
 */
public enum ErrorCode {
    SUCCESS("成功"),
    INVALIAD_PARAM("参数不合法"),
    UNKONWN_ERROR("系统异常"),
    AES_ENCRYPT_ERROR("AES加密异常"),
    AES_DECRYPT_ERROR("AES解密异常")
    ;
    /**
     * 错误描述
     */
    private String desc;

    ErrorCode( String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return name();
    }


    public String getMsg() {
        return desc;
    }
}
