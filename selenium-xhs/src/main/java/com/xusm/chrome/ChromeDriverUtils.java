package com.xusm.chrome;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Chrome驱动工具类
 *
 */
public class ChromeDriverUtils {
    /**
     * 新建浏览驱动
     *
     * @return 谷歌浏览器驱动
     */
    public static ChromeDriver createChromeDriver() {
        // 手动设置 ChromeDriver 路径
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", ChromeConst.CHROME_DRIVER_PATH);

        // 设置 ChromeOptions 进行自定义配置
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); // 反添加爬虫 https://blog.csdn.net/qq_18946341/article/details/127428510

        // 创建 WebDriver 实例并返回
        return new ChromeDriver(options);
    }

    /**
     * 附着在已有的浏览器驱动
     *
     * @return 谷歌浏览器驱动
     */
    public static ChromeDriver attachWebDriver() {
        // 手动设置 ChromeDriver 路径
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", ChromeConst.CHROME_DRIVER_PATH);

        // 配置 ChromeOptions 连接到已打开的浏览器
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); // 反添加爬虫 https://blog.csdn.net/qq_18946341/article/details/127428510
        options.setExperimentalOption("debuggerAddress", "localhost:" + ChromeConst.CHROME_DEBUG_PORT);

        // 启动 WebDriver 并附着到 Chrome
        return new ChromeDriver(options);
    }
}
