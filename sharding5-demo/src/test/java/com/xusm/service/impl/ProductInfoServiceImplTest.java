package com.xusm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xusm.dto.ProductInfoDto;
import com.xusm.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Resource
    private ProductInfoService productInfoService;

    /**
     * 测试添加产品信息
     *
     */
    @Test
    public void test_addProductInfo() {
        // 创建信息
        ProductInfoDto productInfoDto = ProductInfoDto.builder()
                .storeInfoId(1L)
                .productName("sharding5-测试产品1")
                .spec("大号")
                .regionCode("110000")
                .price(new BigDecimal(10))
                .imageUrl("xxx")
                .descript("sharding5-特别好吃好吃好吃")
                .build();
        productInfoService.addProductInfo(productInfoDto);
    }

    /**
     * 测试批量添加产品信息
     *
     */
    @Test
    public void test_addProductInfo_batch() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            // 创建信息
            ProductInfoDto productInfoDto = ProductInfoDto.builder()
                    .storeInfoId(i % 2L)
                    .productName("sharding5-测试产品" + i)
                    .spec("大号")
                    .regionCode("110000")
                    .price(new BigDecimal(10))
                    .imageUrl("xxx")
                    .descript("sharding5-特别好吃好吃好吃" + i)
                    .build();
            productInfoService.addProductInfo(productInfoDto);
        }
    }

    @Test
    public void test_transactional() {
        ProductInfoDto productInfoDto = ProductInfoDto.builder()
                .storeInfoId(1L)
                .productName("sharding5-测试事务1")
                .spec("大号")
                .regionCode("410000")
                .price(new BigDecimal(1))
                .imageUrl("xxx")
                .descript("sharding5-测试事务")
                .build();
        Assertions.assertThrows(RuntimeException.class, ()-> {
            productInfoService.addProductInfo(productInfoDto);
        });
    }

    @Test
    public void test_queryAllProductInfoDto() {
        List<ProductInfoDto> productInfoDtoList = productInfoService.queryAllProductInfoDto();
        for (ProductInfoDto productInfoDto : productInfoDtoList) {
            System.out.println(productInfoDto);
        }
    }

    @Test
    public void test_queryProductInfoPage() {
        IPage<ProductInfoDto> pageResult = productInfoService.queryProductInfoPage(2, 2);
        long total = pageResult.getTotal();
        long pages = pageResult.getPages();
        long current = pageResult.getCurrent();
        long size = pageResult.getSize();
        List<ProductInfoDto> records = pageResult.getRecords();
        log.info("page result: total={}, pages={}, current={}, size={}", total, pages, current, size);
        System.out.println(records);
    }
}
