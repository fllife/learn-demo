package com.mxm.java.learn_demo.valid;

import com.mxm.java.learn_demo.valid.enums.ErrorCode;

/**
 * Create by maxianming 2018/4/26 16:55
 */
public class BaseResp {
    /**
     * 成功标识
     */
    protected Boolean success = Boolean.TRUE;
    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMessage;

    public BaseResp() {

    }

    public BaseResp(Boolean success, String errorCode, String errorMessage) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseResp(Boolean success, ErrorCode errorCode) {
        this.success = success;
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getDesc();
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
