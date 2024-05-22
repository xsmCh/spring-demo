package com.xusm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xusm.dto.ProductInfoDto;
import com.xusm.entity.ProductDesc;
import com.xusm.entity.ProductInfo;
import com.xusm.mapper.ProductDescMapper;
import com.xusm.mapper.ProductInfoMapper;
import com.xusm.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
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

    @Override
    public IPage<ProductInfoDto> queryProductInfoPage(int pageIndex, int pageSize) {
        return productInfoMapper.queryProductInfoPage(new Page<>(pageIndex, pageSize));
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
        if (Objects.equals(productDesc.getDescript(), "测试事务")) {
            int count = productInfoMapper.selectCount(null);
            log.info("test transactional: count product info before rollback: {}", count);
            throw new RuntimeException("测试事务打断");
        }
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
