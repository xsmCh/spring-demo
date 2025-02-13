package com.xusm.service.impl;

import com.xusm.chrome.AttachChromeDriverRunner;
import com.xusm.service.XhsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class XhsServiceImpl implements XhsService {
    @Value("${chrome.debug-address:localhost:9222}")
    private String debuggerAddress;

    @Override
    public String explore() {
        // 同步执行
        new AttachChromeDriverRunner(driver -> {
            driver.get("https://www.xiaohongshu.com/explore");
        }).execute();
        // 响应
        return "success";
    }
}
