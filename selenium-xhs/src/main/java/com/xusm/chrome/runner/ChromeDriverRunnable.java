package com.xusm.chrome.runner;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 浏览器可运行接口
 *
 */
public interface ChromeDriverRunnable {
    /**
     * 浏览器执行
     *
     * @param driver 浏览器驱动
     */
    void execute(ChromeDriver driver);
}
