package me.caixin.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Project Name: SpringBootTest
 * Package Name: me.caixin.configuration.listener
 * Function:
 * User: roy
 * Date: 2017-09-01
 */
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            return;
        }
        logger.info(">>>>>>>>>>>>系统启动完成，onApplicationEvent()","");
    }
}
