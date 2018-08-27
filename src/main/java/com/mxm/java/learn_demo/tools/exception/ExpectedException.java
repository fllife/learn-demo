package com.mxm.java.learn_demo.tools.exception;


import com.mxm.java.learn_demo.tools.enums.ErrorCode;

/**
 * 业务异常
 * Create by maxianming 2018/1/26 14:45
 */
public class ExpectedException extends BaseException {
    public ExpectedException(String errorCode, String errorMessage) {
        super(errorCode,errorMessage);
    }

    public ExpectedException(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMsg());
    }
}
