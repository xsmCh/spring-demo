package com.xusm.web;

import com.xusm.entity.StoreInfo;
import com.xusm.mapper.StoreMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Resource
    private StoreMapper storeMapper;

    @GetMapping("/queryAllStore")
    public List<StoreInfo> queryAllStore() {
        return storeMapper.selectList(null);
    }
}
