package com.xusm.chrome.runner;

import com.xusm.chrome.utils.ChromeDriverUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 浏览器执行器
 *
 */
@Slf4j
public class AttachChromeDriverRunner implements ChromeDriverRunner {
    private final String debuggerAddress;

    private final ChromeDriverRunnable runnable;

    public AttachChromeDriverRunner(String debuggerAddress, ChromeDriverRunnable runnable) {
        this.debuggerAddress = debuggerAddress;
        this.runnable = runnable;
    }

    /**
     * 执行
     *
     */
    public void execute() {
        // 初始化浏览器
        ChromeDriver driver = ChromeDriverUtils.attachWebDriver(debuggerAddress);
        // 执行操作
        runnable.execute(driver);
        // 关闭浏览器
        driver.quit();
    }
}
