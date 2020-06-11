package com.hxzy.repository;

import com.hxzy.pojo.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("123456789");
        detail.setOrderId("123456");
        detail.setProductId("1");
        detail.setProductName("苹果");
        detail.setProductIcon("http://apple.png");
        detail.setProductPrice(new BigDecimal(10));
        detail.setProductQuantity(10);

        OrderDetail result = repository.save(detail);

        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {

        List<OrderDetail> detailList = repository.findByOrderId("123456");

        Assert.assertNotEquals(0, detailList.size());

    }

}