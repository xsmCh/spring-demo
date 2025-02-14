package com.xusm.web;

import com.xusm.service.ChromeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chrome")
public class ChromeController {
    @Resource
    private ChromeService chromeService;

    @GetMapping("/openDebugChrome")
    public void open() {
        chromeService.openDebugChrome();
    }

    @GetMapping("/useDevTools")
    public void useDevTools() {
        chromeService.useDevTools();
    }
}
