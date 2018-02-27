package com.mxm.java.learn_demo.rocketmq.ordermessage;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create by maxianming 2018/2/26 10:02
 */
public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setRetryTimesWhenSendFailed(3);
        producer.start();
        Message msg = new Message("TopicTest1",// topic
                "TagA",// tag
                "messageKey",// key
                ("RocketMQ " + "00001").getBytes());// body
        SendResult sendResult = producer.send(msg);
        System.out.println(sendResult);
        producer.shutdown();

    }
}
