package com.xusm.web;

import com.xusm.service.XhsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/xhs")
public class XhsController {
    @Resource
    private XhsService xhsService;

    @GetMapping("/login")
    public String login() {
        return xhsService.login();
    }
}
