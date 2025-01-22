package com.xusm.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {
    public static ChromeDriver getChromeDriver(boolean isHeadless) {
        // 手动设置 ChromeDriver 路径
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "D:\\Development\\chrome\\chromedriver-win64\\chromedriver.exe"); // 或者 "C:/chromedriver.exe"

        // 设置 ChromeOptions 进行自定义配置
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless");  // 启用无头模式
            options.addArguments("--disable-gpu"); // 禁用 GPU
        }
        options.addArguments("--disable-blink-features=AutomationControlled"); // 反添加爬虫 https://blog.csdn.net/qq_18946341/article/details/127428510

        // 创建 WebDriver 实例并返回
        return new ChromeDriver(options);
    }
}
