package me.caixin.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.apache.log4j.Logger;
/**
 * Project Name: spring-boot-demo
 * Package Name: me.caixin.rocketmq
 * Function:
 * User: roy
 * Date: 2017-09-05
 */
public class RocketMQClientImpl implements MQClientable {

    private static final Logger log = Logger.getLogger(RocketMQClientImpl.class);
    private  DefaultMQPushConsumer consumer;
    private  DefaultMQProducer producer;
    private static Boolean started;
    private MessageListenerConcurrently messageListener;
    private String topicTags;

    public RocketMQClientImpl(DefaultMQPushConsumer consumer, DefaultMQProducer producer) {
        this.consumer = consumer;
        this.producer = producer;
    }


    public void init() throws MQClientException {
        synchronized(started) {
            if(!started) {
                this.startProducer();
                this.startConsumer();
                started = Boolean.TRUE;
            }
        }
    }

    private void startProducer() throws MQClientException {
        if(null != producer) {
            log.info("starting producer..");
            producer.start();
            RocketMQClientImpl.ShutdownHook shutdownHook = new RocketMQClientImpl.ShutdownHook();
            shutdownHook.setProducer(producer);
            Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook));
        }

    }

    private void startConsumer() throws MQClientException {
        if(null != consumer && null != this.getTopicTags()) {
            String[] topicTagArray;
            topicTagArray = this.getTopicTags().split(";");
            String[] topicTagSplit;
            String tag;

            for (String aTopicTagArray : topicTagArray) {
                topicTagSplit = aTopicTagArray.split(",");
                if (topicTagSplit.length <= 1) {
                    tag = "*";
                } else {
                    tag = topicTagSplit[1];
                }

                log.info("consumer subscribes topic:" + topicTagSplit[0] + " and tag:" + tag);
                consumer.subscribe(topicTagSplit[0], tag);
            }

            consumer.registerMessageListener(this.getMessageListener());
            log.info("starting consumer..");
            consumer.start();
        } else {
            log.info("consumer or topicTags is null, consumer will not start");
        }
    }

    public SendResult send(String topic, String tags, String keys, byte[] body) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        Message msg = new Message(topic, tags, keys, body);
        log.info("rocketMQ sending msg=" + msg.toString());
        SendResult sendResult = producer.send(msg);
        log.info("rocketMQ sending result, key=" + keys + ",result=" + sendResult.toString());
        return sendResult;
    }

    public SendResult send(String topic, String tags, String keys, byte[] body, int delayTimeLevel) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        Message msg = new Message(topic, tags, keys, body);
        msg.setDelayTimeLevel(delayTimeLevel);
        log.info("rocketMQ sending msg=" + msg.toString());
        SendResult sendResult = producer.send(msg);
        log.info("rocketMQ sending result, key=" + keys + ",result=" + sendResult.toString());
        return sendResult;
    }

    public DefaultMQPushConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(DefaultMQPushConsumer consumer) {
        synchronized(this) {
            if(null == consumer) {
                consumer = consumer;
            }

        }
    }

    public DefaultMQProducer getProducer() throws MQClientException {
        return producer;
    }

    public void setProducer(DefaultMQProducer producer) {
        synchronized(this) {
            if(null == producer) {
                producer = producer;
            }

        }
    }

    private MessageListenerConcurrently getMessageListener() {
        return this.messageListener;
    }

    public void setMessageListener(MessageListenerConcurrently messageListener) {
        this.messageListener = messageListener;
    }

    private String getTopicTags() {
        return this.topicTags;
    }

    public void setTopicTags(String topicTags) {
        this.topicTags = topicTags;
    }

    static {
        started = Boolean.FALSE;
    }

    private class ShutdownHook implements Runnable {
        private DefaultMQProducer producer;

        private ShutdownHook() {
        }

        void setProducer(DefaultMQProducer producer) {
            this.producer = producer;
        }

        public void run() {
            if(null != this.producer) {
                RocketMQClientImpl.log.info("shuttingdown producer..");
                this.producer.shutdown();
            }

        }
    }
}
