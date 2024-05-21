package com.xusm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("store_info")
public class StoreInfo {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("store_name")
    private String storeName;

    @TableField("reputation")
    private int reputation;

    @TableField("region_code")
    private String regionCode;
}
