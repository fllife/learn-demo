package com.mxm.java.learn_demo.tools.exception;


import com.mxm.java.learn_demo.tools.enums.ErrorCode;

/**
 * 参数异常
 * Create by maxianming 2018/1/26 14:44
 */
public class ParameterException extends BaseException {
    public ParameterException(String errorCode, String errorMessage) {
        super(errorCode,errorMessage);
    }

    public ParameterException(ErrorCode error) {
        super(error.getCode(), error.getMsg());
    }
}
