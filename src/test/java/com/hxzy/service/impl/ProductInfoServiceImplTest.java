package com.hxzy.service.impl;

import com.hxzy.pojo.ProductInfo;
import com.hxzy.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoService repository;

    @Test
    public void findById() {
        ProductInfo info = repository.findById("1");
        Assert.assertEquals("1", info.getProductId());
    }

    @Test
    public void findByOnAll() {
        List<ProductInfo> infoList = repository.findByOnAll();
        Assert.assertNotEquals(0, infoList.size());
    }

    @Test
    public void findByPageable() {
        Sort sort = null;
//        PageRequest request = new PageRequest(0, 2, sort);
//        Page<ProductInfo> info = repository.findByPageable(request);
//        System.out.println(info.getTotalElements());
    }

    @Test
    public void save() {

    }
}