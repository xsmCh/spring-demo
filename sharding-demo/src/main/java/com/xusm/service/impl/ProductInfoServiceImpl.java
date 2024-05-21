package com.xusm.service.impl;

import com.xusm.dto.ProductInfoDto;
import com.xusm.entity.ProductDesc;
import com.xusm.entity.ProductInfo;
import com.xusm.mapper.ProductDescMapper;
import com.xusm.mapper.ProductInfoMapper;
import com.xusm.service.ProductInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    private final ProductInfoMapper productInfoMapper;

    private final ProductDescMapper productDescMapper;

    public ProductInfoServiceImpl(ProductInfoMapper productInfoMapper, ProductDescMapper productDescMapper) {
        this.productInfoMapper = productInfoMapper;
        this.productDescMapper = productDescMapper;
    }

    @Override
    public List<ProductInfoDto> queryAllProductInfoDto() {
        return productInfoMapper.queryAllProductInfoDto();
    }

    @Transactional
    @Override
    public void addProductInfo(ProductInfoDto productInfoDto) {
        // 产品信息
        ProductInfo productInfo = buildProductInfo(productInfoDto);
        // 插入产品信息
        productInfoMapper.insert(productInfo);
        // 构建产品详细信息
        ProductDesc productDesc = buildProductDesc(productInfo.getProductInfoId(), productInfoDto);
        // 插入产品信息
        productDescMapper.insert(productDesc);
    }

    private ProductInfo buildProductInfo(ProductInfoDto productInfoDto) {
        return ProductInfo.builder()
                .storeInfoId(productInfoDto.getStoreInfoId())
                .productName(productInfoDto.getProductName())
                .spec(productInfoDto.getSpec())
                .regionCode(productInfoDto.getRegionCode())
                .price(productInfoDto.getPrice())
                .imageUrl(productInfoDto.getImageUrl())
                .build();
    }

    private ProductDesc buildProductDesc(Long productInfoId, ProductInfoDto productInfoDto) {
        return ProductDesc.builder()
                .productInfoId(productInfoId)
                .storeInfoId(productInfoDto.getStoreInfoId())
                .descript(productInfoDto.getDescript())
                .build();
    }
}
