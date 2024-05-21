package com.xusm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品基本信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("product_info")
public class ProductInfo {
    private static final long serialVersionUID = 1L;

    @TableId("product_info_id")
    private Long productInfoId;

    @TableField("store_info_id")
    private Long storeInfoId;

    @TableField("product_name")
    private String productName;

    @TableField("spec")
    private String spec;

    @TableField("region_code")
    private String regionCode;

    @TableField("price")
    private BigDecimal price;

    @TableField("image_url")
    private String imageUrl;
}
