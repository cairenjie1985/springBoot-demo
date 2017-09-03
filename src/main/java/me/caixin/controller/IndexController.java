package me.caixin.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return name+"say hello !";
    }

}
