package com.xusm.selenium;

import com.xusm.chrome.utils.ChromeDriverUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class GoogleSearchTest {
    @Test
    public void test_open_debug_chrome() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("\"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe\"",
                "--remote-debugging-port=9222",
                "--user-data-dir=\"D:\\tmp\\chrome\\chrome-profile\"");
        builder.redirectErrorStream(true);
        builder.start();
    }

    // 但是无头模式不行
    @Test
    public void testGoogleSearch_newWebDriver() {
        // 初始化浏览器驱动
        WebDriver driver = ChromeDriverUtils.createChromeDriver(false);
        // 打开 Google 页面
        driver.get("https://www.google.com");
        // 查找搜索框并输入搜索内容
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        // 提交搜索
        searchBox.submit();
        // 等待页面加载，并验证页面标题是否包含关键词
        assertTrue(driver.getTitle().contains("Selenium WebDriver"));
        // 退出驱动
        driver.quit();
    }

    @Test
    public void testGoogleSearch_attachWebDriver() {
        // 初始化浏览器驱动
        WebDriver driver = ChromeDriverUtils.attachWebDriver("localhost:9222");
        // 打开 Google 页面
        driver.get("https://www.google.com");
        // 查找搜索框并输入搜索内容
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        // 提交搜索
        searchBox.submit();
        // 等待页面加载，并验证页面标题是否包含关键词
        assertTrue(driver.getTitle().contains("Selenium WebDriver"));
        // 退出驱动
        driver.quit();
    }
}
