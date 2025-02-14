package com.xusm.chrome;

import java.io.IOException;

/**
 * Chrome浏览器工具类
 *
 */
public class ChromeUtils {
    /**
     * 启动Debug浏览器
     *
     */
    public static void openDebugChrome() {
        try {
            ProcessBuilder builder = new ProcessBuilder(ChromeConst.CHROME_EXE_PATH,
                    "--remote-debugging-port=" + ChromeConst.CHROME_DEBUG_PORT,
                    "--user-data-dir=" + ChromeConst.CHROME_DEBUG_USER_DATA_DIR);
            builder.redirectErrorStream(true);
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
