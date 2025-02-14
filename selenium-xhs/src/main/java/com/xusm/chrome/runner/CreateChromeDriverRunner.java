package com.xusm.chrome.runner;

import com.xusm.chrome.ChromeDriverUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 浏览器执行器
 *
 */
@Slf4j
public class CreateChromeDriverRunner implements ChromeDriverRunner {
    private final boolean quit;

    private final ChromeDriverRunnable runnable;

    public CreateChromeDriverRunner(ChromeDriverRunnable runnable) {
        this(true, runnable);
    }

    public CreateChromeDriverRunner(boolean quit, ChromeDriverRunnable runnable) {
        this.quit = quit;
        this.runnable = runnable;
    }

    /**
     * 执行
     *
     */
    public void execute() {
        // 初始化浏览器
        ChromeDriver driver = ChromeDriverUtils.createChromeDriver();
        // 执行操作
        runnable.execute(driver);
        // 关闭浏览器
        if (quit) {
            driver.quit();
        }
    }
}
