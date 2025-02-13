package com.xusm.service.impl;

import com.xusm.chrome.CreateChromeDriverRunner;
import com.xusm.service.GoogleService;
import com.xusm.chrome.SleepUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class GoogleServiceImpl implements GoogleService {
    @Override
    public String googleSearch(String query) {
        // 同步执行
        new CreateChromeDriverRunner(driver -> {
            // 打开 Google 页面
            driver.get("https://www.google.com");
            // 查找搜索框并输入搜索内容
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Selenium WebDriver");
            // 提交搜索
            searchBox.submit();
            // 关闭前等待
            SleepUtils.sleep(3000L);
        }).execute();
        // 响应
        return "success";
    }
}
