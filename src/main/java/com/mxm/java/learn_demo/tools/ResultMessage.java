package com.mxm.java.learn_demo.tools;

import com.mxm.java.learn_demo.tools.enums.ErrorCode;
import lombok.Data;

/**
 * Create by maxianming 2018/4/26 16:55
 */
@Data
public class ResultMessage<T> {
    /**
     * 响应码
     */
    protected String code = ErrorCode.SUCCESS.getCode();

    /**
     * 响应信息
     */
    protected String msg = ErrorCode.SUCCESS.getMsg();

    protected T data;

    public ResultMessage() {

    }
    /**
     * 是否成功的判断
     * @author maxianming
     * @param
     * @return
     * @date 2018/8/14 10:03
     */
    public boolean isSuccess() {
        if (ErrorCode.SUCCESS.getCode().equals(code) && ErrorCode.SUCCESS.getMsg().equals(msg)) {
            return true;
        }
        return false;
    }
    public ResultMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }




}
