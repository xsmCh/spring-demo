package com.xusm.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepUtils {
    /**
     * 安静睡眠
     *
     * @param time 睡眠时间
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.info("sleep interrupted", e);
        }
    }
}
