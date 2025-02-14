# selenium-xhs

> 该项目使用selenium爬取小红书网站的公开信息

## 1、获取Chrome与ChromeDriver

1）Chrome for Testing：可靠下载，实现浏览器自动化

路径：https://developer.chrome.com/blog/chrome-for-testing?hl=zh-cn

正式使用的Chrome浏览器会自动更新，这有利于用户，但不利于开发者。用户非常高兴地知道自己运行的是最新且安全的浏览器版本，该版本包含最新的 Web 平台功能、浏览器功能，并随时都能修复问题。开发者希望在重复的测试运行期间获得一致且可重现的结果，但如果浏览器可执行文件或二进制文件决定在两次运行之间自行更新，这种情况可能不会发生。为解决上述问题，Chrome for Testing 是 Chrome 的专用版本，针对测试用例，不自动更新，已集成到 Chrome 发布流程中，适用于每个 Chrome 版本。

2）Chrome for Testing 可用性信息中心

地址：https://googlechromelabs.github.io/chrome-for-testing/

Chrome for Testing 可用性信息中心列出了每个 Chrome 发布渠道的最新可用跨平台 Chrome for Testing 版本和资产。

3）JSON API（全部版本信息）

地址：https://github.com/GoogleChromeLabs/chrome-for-testing#json-api-endpoints

| Endpoint                                                     | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [`known-good-versions.json`](https://googlechromelabs.github.io/chrome-for-testing/known-good-versions.json) | The versions for which all CfT assets are available for download. Useful for bisecting. |
| [`known-good-versions-with-downloads.json`](https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json) | Same as above, but with an extra `downloads` property for each version, listing the full download URLs per asset. |
| [`last-known-good-versions.json`](https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions.json) | The latest versions for which all CfT assets are available for download, for each Chrome release channel (Stable/Beta/Dev/Canary). |
| [`last-known-good-versions-with-downloads.json`](https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json) | Same as above, but with an extra `downloads` property for each channel, listing the full download URLs per asset. |
| [`latest-patch-versions-per-build.json`](https://googlechromelabs.github.io/chrome-for-testing/latest-patch-versions-per-build.json) | The latest versions for which all CfT assets are available for download, for each known combination of `MAJOR.MINOR.BUILD` versions. |
| [`latest-patch-versions-per-build-with-downloads.json`](https://googlechromelabs.github.io/chrome-for-testing/latest-patch-versions-per-build-with-downloads.json) | Same as above, but with an extra `downloads` property for each version, listing the full download URLs per asset. |
| [`latest-versions-per-milestone.json`](https://googlechromelabs.github.io/chrome-for-testing/latest-versions-per-milestone.json) | The latest versions for which all CfT assets are available for download, for each Chrome milestone. |
| [`latest-versions-per-milestone-with-downloads.json`](https://googlechromelabs.github.io/chrome-for-testing/latest-versions-per-milestone-with-downloads.json) | Same as above, but with an extra `downloads` property for each milestone, listing the full download URLs per asset. |

4）下载资源

下载指定版本chrome，chromedriver

## 2、创建浏览器启动

```java
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
```

> 在考虑能不能指定浏览器的user-data-dir

## 3、附着浏览器启动

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

## 4、启动DevTools

> 正在研究



## 5、总结

目前只能简单总结。

使用java的方式，开发效率没有python的高，因为python是脚本改完就可以直接执行了，java的还要重新启动。

先把selenium的使用搞清楚吧。