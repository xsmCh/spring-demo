package com.xusm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xusm.dto.ProductInfoDto;

import java.util.List;

public interface ProductInfoService {
    /**
     * 查询所有产品信息
     *
     * @return 产品信息列表
     */
    List<ProductInfoDto> queryAllProductInfoDto();

    /**
     * 分页查询产品信息
     *
     * @param pageIndex 页数
     * @param pageSize 单页大小
     * @return 产品信息
     */
    IPage<ProductInfoDto> queryProductInfoPage(int pageIndex, int pageSize);

    /**
     * 添加产品信息
     *
     * @param productInfoDto 产品信息
     */
    void addProductInfo(ProductInfoDto productInfoDto);
}
