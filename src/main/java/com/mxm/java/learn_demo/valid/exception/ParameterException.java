package com.mxm.java.learn_demo.valid.exception;


/**
 * 参数异常
 * Create by maxianming 2018/1/26 14:44
 */
public class ParameterException extends BaseException {
    public ParameterException(String errorCode, String errorMessage) {
        super(errorCode,errorMessage);
    }
}
