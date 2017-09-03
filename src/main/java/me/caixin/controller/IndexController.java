package me.caixin.controller;

import me.caixin.entity.AccountEntity;
import me.caixin.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project Name: SpringBootTest
 * Package Name: me.caixin.controller
 * Function:
 * User: roy
 * Date: 2017-09-01
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Resource
    private AccountService accountService;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return name+"say hello !";
    }

    @RequestMapping("/getName/{id}")
    public String getName(@PathVariable int id){
     String name =  accountService.getNameById(id);
    return StringUtils.isNotBlank(name)? name+"say hi" :"查无用户";
    }

    @RequestMapping("/getAccount/{name}")
    public @ResponseBody List<AccountEntity> getAccount(@PathVariable String name){
        return accountService.selectAccountByName(name);
    }

}
