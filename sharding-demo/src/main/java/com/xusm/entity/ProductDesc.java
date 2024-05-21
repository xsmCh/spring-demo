package com.xusm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品描述
 */
@Data
@TableName("product_descript")
public class ProductDesc {

    private Long id;

    /**
     * 所属商品id
     */
    private Long productInfoId;

    /**
     * 商品描述
     */
    private String descript;

    /**
     * 所属店铺id
     */
    private Long storeInfoId;

}
