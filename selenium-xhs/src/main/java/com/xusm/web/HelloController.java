package com.xusm.web;


import com.xusm.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Resource
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello() {
        return helloService.sayHello();
    }
}
