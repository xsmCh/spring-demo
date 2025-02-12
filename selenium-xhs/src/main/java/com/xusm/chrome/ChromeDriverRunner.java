package com.xusm.chrome;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 浏览器执行器
 *
 */
@Slf4j
public class ChromeDriverRunner {
    @Setter
    private long sleepBeforeQuite;

    private final ChromeDriverRunnable runnable;

    public ChromeDriverRunner(ChromeDriverRunnable runnable) {
        this(1000L, runnable);
    }

    public ChromeDriverRunner(long sleepBeforeQuite, ChromeDriverRunnable runnable) {
        this.sleepBeforeQuite = sleepBeforeQuite;
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
        // 关闭前睡眠
        sleep();
        // 关闭浏览器
        driver.quit();
    }

    private void sleep() {
        try {
            Thread.sleep(sleepBeforeQuite);
        } catch (InterruptedException e) {
            log.info("sleep interrupted", e);
        }
    }
}
