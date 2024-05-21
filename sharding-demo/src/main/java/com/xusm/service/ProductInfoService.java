package com.xusm.service;

import com.xusm.dto.ProductInfoDto;

import java.util.List;

public interface ProductInfoService {
    /**
     * 查询所有产品信息
     *
     * @return
     */
    List<ProductInfoDto> queryAllProductInfoDto();
    /**
     * 添加产品信息
     *
     * @param productInfoDto 产品信息
     */
    void addProductInfo(ProductInfoDto productInfoDto);
}
