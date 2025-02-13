# selenium-xhs

> 该项目使用selenium爬取小红书网站的公开信息

#### 1、准备驱动环境



#### 2、附着启动

1）启动 Chrome 并开启远程调试端口

```bat
"C:\Program Files\Google\Chrome\Application\chrome.exe" --remote-debugging-port=9222 --user-data-dir="D:\tmp\chrome\chrome-profile"
```

- 如果启动chrome浏览器的路径有空格，用引号引起来
- `--remote-debugging-port=9222`：开启调试端口
- `--user-data-dir="D:\tmp\chrome\chrome-profile"`：设置一个新的浏览器用户数据目录，这样浏览器就与日常使用的浏览器区别开了

2）附着启动

```java
/**
 * 附着在已有的浏览器驱动
 *
 * @param debuggerAddress 调试地址（先手动启动浏览器）
 * @return 谷歌浏览器驱动
 */
public static ChromeDriver attachWebDriver(String debuggerAddress) {
    // 手动设置 ChromeDriver 路径
    System.setProperty("webdriver.http.factory", "jdk-http-client"); // jdk-http=client
    System.setProperty("webdriver.chrome.driver", "D:\\Development\\chrome\\chromedriver-win64\\chromedriver.exe"); // chrome驱动位置

    // 配置 ChromeOptions 连接到已打开的浏览器
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("debuggerAddress", debuggerAddress);

    // 启动 WebDriver 并附着到 Chrome
    return new ChromeDriver(options);
}
```

