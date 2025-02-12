package com.xusm.service;

public interface GoogleService {
    /**
     * 谷歌搜索
     *
     * @param query 查询内容
     * @return 响应
     */
    String googleSearch(String query);
}
