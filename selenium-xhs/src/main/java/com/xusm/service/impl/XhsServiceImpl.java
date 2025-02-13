package com.xusm.service.impl;

import com.xusm.chrome.AttachChromeDriverRunner;
import com.xusm.service.XhsService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class XhsServiceImpl implements XhsService {
    @Override
    public String explore() {
        // 同步执行
        new AttachChromeDriverRunner(driver -> {
            // 打开网站
            driver.get("https://www.xiaohongshu.com/explore");
        }).execute();
        // 响应
        return "success";
    }

    @Override
    public String parseExploreNoteItem() {
        // 同步执行
        new AttachChromeDriverRunner(driver -> {
            // 获取node-ite内容
            List<WebElement> elements = driver.findElements(By.className("note-item"));
            // todo 进一步解析node-item
            for (WebElement element : elements) {
                String text = element.getText();
                log.info("note-item: {}", text);
            }
        }).execute();
        // 响应
        return "success";
    }
}
