package com.mxm.java.learn_demo.valid.exception;


import com.mxm.java.learn_demo.valid.enums.ErrorCode;

/**
 * 业务异常
 * Create by maxianming 2018/1/26 14:45
 */
public class BusinessException extends BaseException {
    public BusinessException(String errorCode, String errorMessage) {
        super(errorCode,errorMessage);
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getDesc());
    }
}
