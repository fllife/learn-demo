package com.mxm.java.learn_demo.tools.monitor;

/**
 * @author maxianming
 * @date 2018/8/15 14:48
 */
public interface ExceptionReport {
     /**
      * 推送异常
      * @author maxianming
      * @param
      * @return
      * @date 2018/8/15 16:07
      */
     void pushMessage(ExceptionMesssage messsage);
     /**
      * 获取异常消息
      * @author maxianming
      * @param
      * @return
      * @date 2018/8/15 16:07
      */
     ExceptionMesssage pullMessage();
}
