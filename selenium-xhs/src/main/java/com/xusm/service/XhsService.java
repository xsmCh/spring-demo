package com.xusm.service;

public interface XhsService {

    /**
     * 打开explore页
     *
     * @return 响应
     */
    String explore();

    /**
     * 解析explore页node-item
     *
     * @return 响应
     */
    String parseExploreNoteItem();
}
