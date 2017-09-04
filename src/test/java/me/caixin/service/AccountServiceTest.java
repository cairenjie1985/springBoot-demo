package me.caixin.service;

import com.alibaba.fastjson.JSON;
import me.caixin.entity.AccountEntity;
import me.caixin.framework.AbstractJUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Created by roy on 2017/9/4.
 */
@EnableCaching
@EnableAutoConfiguration
public class AccountServiceTest extends AbstractJUnit {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AccountService accountService;

    @Test
    public void testObj() throws Exception {
        AccountEntity accountEntity = accountService.getAccount(1);
        HashOperations<String, String, String> operations=redisTemplate.opsForHash();
        operations.put("demo:account",String.valueOf(accountEntity.getId()), JSON.toJSONString(accountEntity));
        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("demo:account");
        Assert.assertTrue("不存在",exists);
        AccountEntity accountEntityCache =JSON.parseObject(String.valueOf(operations.get("demo:account","1")) ,AccountEntity.class);
        Assert.assertEquals("json",accountEntityCache.getNickName());
    }
}