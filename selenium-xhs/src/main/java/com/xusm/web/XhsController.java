package com.xusm.web;

import com.xusm.service.XhsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/xhs")
public class XhsController {
    @Resource
    private XhsService xhsService;

    /**
     * 打开explore页
     *
     * @return 响应
     */
    @GetMapping("/explore")
    public String explore() {
        return xhsService.explore();
    }

    /**
     * 解析explore页node-item
     *
     * @return 响应
     */
    @GetMapping("/parseExploreNoteItem")
    public String parseExploreNoteItem() {
        return xhsService.parseExploreNoteItem();
    }
}
