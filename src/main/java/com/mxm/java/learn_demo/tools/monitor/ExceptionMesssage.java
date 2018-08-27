package com.mxm.java.learn_demo.tools.monitor;

import lombok.Data;

import java.util.Date;

/**
 * @author maxianming
 * @date 2018/8/15 14:51
 */
@Data
public class ExceptionMesssage {
    /**
     * 异常对象
     * @author maxianming
     * @param
     * @return 
     * @date 2018/8/16 8:39
     */
    private Throwable throwable;
    /**
     * 异常发生时间
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 8:39
     */
    private Date occurTime;
}
