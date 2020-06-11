package com.hxzy.repository;

import com.hxzy.pojo.ProductInfo;
import com.hxzy.repository.ProductInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    ProductInfoRepository infoDao;

    @Test
    public void saveTest() {
        ProductInfo info = new ProductInfo();
        info.setProductId("123456");
        info.setProductName("皮蛋瘦肉粥");
        info.setProductPrice(new BigDecimal(3.2));
        info.setProductStock(100);
        info.setProductDescription("粥好喝也养生");
        info.setProductIcon("http://shourouzhou.png");
        info.setProductStatus(0);
        info.setCategoryType(123456);
        infoDao.save(info);
    }

    @Test
    public void findByProductStatus() {

    }
}