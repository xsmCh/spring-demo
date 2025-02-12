package com.xusm.selenium;

import com.xusm.chrome.ChromeDriverUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class GoogleSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // 初始化 WebDriver
        driver = ChromeDriverUtils.getChromeDriver(false);
    }

    // 但是无头模式不行
    @Test
    public void testGoogleSearch() {
        // 打开 Google 页面
        driver.get("https://www.google.com");

        // 查找搜索框并输入搜索内容
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");

        // 提交搜索
        searchBox.submit();

        // 等待页面加载，并验证页面标题是否包含关键词
        assertTrue(driver.getTitle().contains("Selenium WebDriver"));
    }

    @AfterEach
    public void tearDown() {
        // 测试完成后关闭浏览器
        if (driver != null) {
            driver.quit();
        }
    }
}
