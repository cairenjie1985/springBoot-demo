package me.caixin.rocketmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * Project Name: spring-boot-demo
 * Package Name: me.caixin.rocketmq
 * Function:
 * User: roy
 * Date: 2017-09-05
 */
public interface MQClientable {
    
    SendResult send(String var1, String var2, String var3, byte[] var4)
            throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    SendResult send(String var1, String var2, String var3, byte[] var4, int var5)
            throws MQClientException, RemotingException, MQBrokerException, InterruptedException;

    void init() throws MQClientException;
}
