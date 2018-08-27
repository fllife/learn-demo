package com.mxm.java.learn_demo.tools.exception;


import com.mxm.java.learn_demo.tools.enums.ErrorCode;

/**
 * @author maxianming
 * @date 2018/8/14 9:32
 */
public class SystemException extends BaseException{
    public SystemException(String errorCode, String errorMessage) {
        super(errorCode,errorMessage);
    }

    public SystemException(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMsg());
    }
}
