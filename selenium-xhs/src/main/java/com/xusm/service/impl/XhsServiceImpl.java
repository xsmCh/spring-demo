package com.xusm.service.impl;

import com.xusm.chrome.ChromeDriverRunner;
import com.xusm.service.XhsService;
import com.xusm.utils.SleepUtils;
import org.springframework.stereotype.Service;

@Service
public class XhsServiceImpl implements XhsService {
    @Override
    public String login() {
        // 同步执行
        new ChromeDriverRunner(false, driver -> {
            // 打开 Google 页面
            driver.get("https://www.xiaohongshu.com/explore");
            // 睡眠等待页面稳定
            SleepUtils.sleep(3000L);
            // 查找搜索框并输入搜索内容

        }).execute();
        // 响应
        return "success";
    }
}
