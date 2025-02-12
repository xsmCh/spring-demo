package com.xusm.chrome;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 浏览器执行器
 *
 */
@Slf4j
public class ChromeDriverRunner {
    private final boolean quit;

    private final ChromeDriverRunnable runnable;

    public ChromeDriverRunner(ChromeDriverRunnable runnable) {
        this(true, runnable);
    }

    public ChromeDriverRunner(boolean quit, ChromeDriverRunnable runnable) {
        this.quit = quit;
        this.runnable = runnable;
    }

    /**
     * 执行
     *
     */
    public void execute() {
        // 初始化浏览器
        ChromeDriver driver = ChromeDriverUtils.getChromeDriver(false);
        // 执行操作
        runnable.execute(driver);
        // 关闭浏览器
        if (quit) {
            driver.quit();
        }
    }
}
