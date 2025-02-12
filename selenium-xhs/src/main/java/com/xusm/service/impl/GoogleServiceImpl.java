package com.xusm.service.impl;

import com.xusm.chrome.ChromeDriverRunner;
import com.xusm.service.GoogleService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class GoogleServiceImpl implements GoogleService {
    @Override
    public String googleSearch(String query) {
        // 同步执行
        new ChromeDriverRunner(3000L, driver -> {
            // 打开 Google 页面
            driver.get("https://www.google.com");
            // 查找搜索框并输入搜索内容
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Selenium WebDriver");
            // 提交搜索
            searchBox.submit();
        }).execute();
        // 响应
        return "success";
    }
}
