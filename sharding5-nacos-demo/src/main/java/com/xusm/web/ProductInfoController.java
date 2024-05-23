package com.xusm.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xusm.dto.ProductInfoDto;
import com.xusm.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product-info")
public class ProductInfoController {
    @Resource
    private ProductInfoService productInfoService;

    @GetMapping("/query-all")
    public List<ProductInfoDto> queryAllProductInfoDto() {
        List<ProductInfoDto> list = productInfoService.queryAllProductInfoDto();
        log.info("product info size: {}", list);
        return list;
    }

    @GetMapping("/query-page")
    public IPage<ProductInfoDto> queryProductInfoPage(int pageIndex, int pageSize) {
        IPage<ProductInfoDto> page = productInfoService.queryProductInfoPage(pageIndex, pageSize);
        log.info("page result: total={}, pages={}, current={}, size={}", page.getTotal(), page.getPages(), page.getCurrent(), page.getSize());
        return page;
    }
}
