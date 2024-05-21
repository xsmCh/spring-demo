package com.xusm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("store_info")
public class StoreInfo {
    private Integer id;

    private String storeName;

    private int reputation;

    private String regionCode;
}
