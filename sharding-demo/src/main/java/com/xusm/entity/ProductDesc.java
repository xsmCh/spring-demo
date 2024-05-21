package com.xusm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("product_descript")
public class ProductDesc {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("store_info_id")
    private Long storeInfoId;

    @TableField("product_info_id")
    private Long productInfoId;

    @TableField("descript")
    private String descript;
}
