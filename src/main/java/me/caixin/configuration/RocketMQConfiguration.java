package me.caixin.configuration;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import me.caixin.listener.MessageListener;
import me.caixin.rocketmq.RocketMQClientImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

/**
 * Project Name: spring-boot-demo
 * Package Name: me.caixin.configuration
 * Function:
 * User: roy
 * Date: 2017-09-05
 */
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
@PropertySource(value = "classpath:/config/rocketmq.properties")
public class RocketMQConfiguration {

    @Order(1)
    @Bean(name = "consumer")
    public DefaultMQPushConsumer defaultMQPushConsumer(){
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
        defaultMQPushConsumer.setConsumerGroup(this.consumerGroup);
        defaultMQPushConsumer.setNamesrvAddr(this.addr);
        return defaultMQPushConsumer;
    }

    @Order(1)
    @Bean(name = "producer")
    public DefaultMQProducer defaultMQProducer(){
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setProducerGroup(this.producerGroup);
        defaultMQProducer.setNamesrvAddr(this.addr);
        return defaultMQProducer;
    }

    @Order(1)
    @Bean(name="messageListener")
    public MessageListener  messageListener(){
        return new MessageListener();
    }

    @Order(2)
    @Bean(name = "mqClient",initMethod = "init")
    public RocketMQClientImpl rocketMQClient(){
        RocketMQClientImpl rocketMQClient = new RocketMQClientImpl(this.defaultMQPushConsumer(), this.defaultMQProducer());
        rocketMQClient.setMessageListener(this.messageListener());
        rocketMQClient.setTopicTags(this.topicTags);
        return rocketMQClient;
    }


    private String consumerGroup ;

    private String addr;

    private String producerGroup;

    private String topicTags;

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public String getTopicTags() {
        return topicTags;
    }

    public void setTopicTags(String topicTags) {
        this.topicTags = topicTags;
    }
}
