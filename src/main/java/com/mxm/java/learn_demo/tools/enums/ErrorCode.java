package com.mxm.java.learn_demo.tools.enums;

/**
 * Create by maxianming 2018/4/26 16:53
 */
public enum ErrorCode {
    SUCCESS("成功"),
    INVALIAD_PARAM("参数不合法"),
    UNKONWN_ERROR("系统异常"),
    AES_ENCRYPT_ERROR("AES加密异常"),
    AES_DECRYPT_ERROR("AES解密异常"),
    NULL_OBJ_ARGUMENT("空对象"),
    SYS_EXCEPTION("系统异常"),
    EMPTY_COLLECTION_ARGUMENT("集合为空"),
    DATE_PARSE_ERROR("日期格式化错误");

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
