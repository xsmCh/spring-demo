package com.xusm.service.impl;

import com.xusm.chrome.ChromeUtils;
import com.xusm.chrome.CreateChromeDriverRunner;
import com.xusm.service.ChromeService;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.RequestWillBeSent;
import org.openqa.selenium.devtools.v132.network.model.ResponseReceived;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChromeServiceImpl implements ChromeService {
    @Override
    public void openDebugChrome() {
        ChromeUtils.openDebugChrome();
    }

    @Override
    public void useDevTools() {
        // 同步执行
        new CreateChromeDriverRunner(driver -> {
            DevTools devTools = driver.getDevTools();
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
            // 监听请求
            devTools.addListener(Network.requestWillBeSent(), (RequestWillBeSent request) -> {
                System.out.println("拦截到请求: " + request.getRequest().getUrl());
            });
            // 监听响应
            devTools.addListener(Network.responseReceived(), (ResponseReceived response) -> {
                System.out.println("拦截到响应: " + response.getResponse().getUrl());
            });
            // 打开网站
            driver.get("https://www.xiaohongshu.com/explore");
        }).execute();
    }
}
