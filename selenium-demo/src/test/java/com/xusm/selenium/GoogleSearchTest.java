package com.xusm.selenium;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class GoogleSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // 初始化 WebDriver
        driver = getDriver();
    }

    public WebDriver getDriver() {
        // 手动设置 ChromeDriver 路径
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "D:\\Development\\chrome\\chromedriver-win64\\chromedriver.exe"); // 或者 "C:/chromedriver.exe"

        // 设置 ChromeOptions 进行自定义配置
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // 启用无头模式
        options.addArguments("--disable-gpu"); // 禁用 GPU

        // 创建 WebDriver 实例并返回
        return new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() {
        // 打开 Google 页面
        driver.get("https://www.bilibili.com");

        // 查找搜索框并输入搜索内容
        WebElement searchBox = driver.findElement(By.className("nav-search-input"));
        searchBox.sendKeys("Selenium WebDriver");

        // 提交搜索
        searchBox.submit();

        // 获取当前页面的 HTML 源代码
        String pageSource = driver.getPageSource();
        log.info("Page is:{}{}", System.lineSeparator(), pageSource);

        // 等待页面加载，并验证页面标题是否包含关键词
        assertTrue(pageSource.contains("Selenium WebDriver"));
    }

    @AfterEach
    public void tearDown() {
        // 测试完成后关闭浏览器
        if (driver != null) {
            driver.quit();
        }
    }
}
