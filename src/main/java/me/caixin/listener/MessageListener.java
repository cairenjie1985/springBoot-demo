package me.caixin.listener;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * Project Name: spring-boot-demo
 * Package Name: me.caixin.listener
 * Function:
 * User: roy
 * Date: 2017-09-05
 */
public class MessageListener implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        Message msg = msgs.get(0);
        String body = null;
        try {
            body = new String(msg.getBody(), "utf-8");
            if("test".equals(msg.getTopic())){
                logger.info(body);
            }
        } catch (Exception e) {
            logger.error("consumeMessage error, msg=" + msg.toString(), e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
