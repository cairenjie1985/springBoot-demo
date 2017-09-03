package me.caixin.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Name: SpringBootTest
 * Package Name: me.caixin.configuration.listener
 * Function:
 * User: roy
 * Date: 2017-09-01
 */
public class MyListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("starting MyListener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
