package com.xusm.service.impl;

import com.xusm.service.ChromeService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChromeServiceImpl implements ChromeService {
    @Override
    public void openDebugChrome() {
        try {
            ProcessBuilder builder = new ProcessBuilder("\"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe\"",
                    "--remote-debugging-port=9222",
                    "--user-data-dir=\"D:\\tmp\\chrome\\chrome-profile\"");
            builder.redirectErrorStream(true);
            builder.start();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
