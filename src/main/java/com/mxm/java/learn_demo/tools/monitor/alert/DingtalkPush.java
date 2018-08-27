package com.mxm.java.learn_demo.tools.monitor.alert;

import com.alibaba.fastjson.JSON;
import com.brightcns.wuxi.citizencard.common.feature.monitor.ExceptionMesssage;
import com.brightcns.wuxi.citizencard.common.feature.monitor.util.SystemInfo;
import com.brightcns.wuxi.citizencard.common.feature.util.DateUtils;
import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.message.Message;
import com.dingtalk.chatbot.message.TextMessage;
import com.mxm.java.learn_demo.tools.monitor.ExceptionReport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author maxianming
 * @date 2018/8/15 16:17
 */
@Slf4j
public class DingtalkPush {
    private DingtalkChatbotClient client = new DingtalkChatbotClient();
    private DingtalkProperties dingtalkProperties;
    private ExceptionReport exceptionReport;

    public final long DEFAULT_INTERNAL = 5000;
    // 内容长度
    private final int MAX_INFO_LENGTH = 500;
    /**
     * 拉取时间间隔
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 8:02
     */
    private long pullInternal;
    
    /**
     * 是否启动标志
     * @author maxianming
     * @param
     * @return 
     * @date 2018/8/16 8:02
     */
    public volatile boolean start = false;
    
    public DingtalkPush(ExceptionReport exceptionReport, DingtalkProperties dingtalkProperties) {
        this.exceptionReport = exceptionReport;
        this.dingtalkProperties = dingtalkProperties;
        this.init();
    }

    private void init() {
        if(dingtalkProperties.getPullInterval() > 0) {
            this.pullInternal = dingtalkProperties.getPullInterval();
        } else {
            this.pullInternal = DEFAULT_INTERNAL;
        }
        this.start = true;
        start();
    }
    /**
     * 开始告警推送
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 8:03
     */
    public void start() {
        new Thread(()->{
            while(start) {
                ExceptionMesssage exceptionMesssage = exceptionReport.pullMessage();
                if (exceptionMesssage == null) {
                    try {
                        Thread.sleep(pullInternal);
                    } catch (InterruptedException e) {
                        log.error("拉取消息异常",e);
                    }
                } else {
                    sendAlertMesssae(exceptionMesssage);
                }
            }
        }).start();
    }
    /**
     * 停止告警推送
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 8:03
     */
    public void stop() {
        log.debug("停止拉取消息");
        this.start = false;
    }
    
    private void sendAlertMesssae(ExceptionMesssage exceptionMesssage) {
        Message message = genetateAletContent(exceptionMesssage);
        try {
            long start = System.currentTimeMillis();
            if (log.isDebugEnabled()) {
                log.debug("发送钉钉告警信息:{}", JSON.toJSON(message));
            }
            SendResult sendResult = client.send(dingtalkProperties.getUrl(), message);
            if (log.isDebugEnabled()) {
                log.info("发送钉钉告警结果:{}, 耗时:{}ms", JSON.toJSON(sendResult), System.currentTimeMillis() - start);
            }
        } catch (IOException e) {
            log.error("发送钉钉告警信息失败", e);
        }
    }

    private TextMessage genetateAletContent(ExceptionMesssage exceptionMesssage) {
        Throwable throwable = exceptionMesssage.getThrowable();
        String stackTrace = ExceptionUtils.getStackTrace(throwable);
        if (dingtalkProperties.getLevel().equals(DingtalkProperties.Level.SHORT)) {
            if (stackTrace!= null && stackTrace.length() > MAX_INFO_LENGTH) {
                stackTrace = stackTrace.substring(0, MAX_INFO_LENGTH);
            }
        }
        String content =  new StringBuffer("HostName:").append(SystemInfo.getHostName()).append("\n")
                              .append("Application:").append( dingtalkProperties.getApplicationName()).append("\n")
                              .append("Env:").append(dingtalkProperties.getEnv()).append("\n")
                              .append("OccurTime:").append(DateUtils.format(exceptionMesssage.getOccurTime(), DateUtils.YMDHMS)).append("\n")
                              .append("Exception:").append(stackTrace).toString();

        TextMessage message = new TextMessage(content);
        List<String> phone = dingtalkProperties.getPhone();
        if (!CollectionUtils.isEmpty(phone)) {
            message.setAtMobiles(phone);
        }
        if (dingtalkProperties.isAtAll()) {
            message.setIsAtAll(true);
        }
        return message;
    }
}
