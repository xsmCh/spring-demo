package com.xusm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xusm.dto.ProductInfoDto;
import com.xusm.entity.ProductInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    List<ProductInfoDto> queryAllProductInfoDto();

    IPage<ProductInfoDto> queryProductInfoPage(Page<?> page);
}
