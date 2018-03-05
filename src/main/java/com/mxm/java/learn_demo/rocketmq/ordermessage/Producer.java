package com.mxm.java.learn_demo.rocketmq.ordermessage;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * Create by maxianming 2018/2/27 14:20
 */
public class Producer {

    private static final String NAMESRV_ADDR = "127.0.0.1:9876";

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("orderProducerGroupName");
        producer.setNamesrvAddr(NAMESRV_ADDR);
        producer.start();
        for (int i = 0; i < 8; i++) {
            Message message = new Message("TopicTest1", "TagB", "messageKey", ("orderMessage_" + i).getBytes());
            producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message message, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            },i);
        }
        producer.shutdown();
    }
}
