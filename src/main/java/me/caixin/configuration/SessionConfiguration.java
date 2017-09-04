package me.caixin.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Project Name: spring-boot-demo
 * Package Name: me.caixin.configuration
 * Function: session 配置
 * User: roy.cai
 * Date: 2017-09-04
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class SessionConfiguration {
}
