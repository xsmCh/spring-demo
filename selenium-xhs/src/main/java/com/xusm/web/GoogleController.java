package com.xusm.web;

import com.xusm.service.GoogleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/google")
public class GoogleController {
    @Resource
    private GoogleService googleService;

    /**
     * 谷歌搜索
     *
     * @param query 查询内容
     * @return 响应
     */
    @GetMapping("/googleSearch")
    public String googleSearch(String query) {
        return googleService.googleSearch(query);
    }
}
