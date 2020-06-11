package com.hxzy.repository;

import com.hxzy.pojo.OrderMaster;
import com.hxzy.repository.OrderMasterRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository masterRepository;

    @Test
    public void saveTest() {

        OrderMaster master = new OrderMaster();

        master.setOrderId("123456");
        master.setBuyerName("李龙跃");
        master.setBuyerPhone("10086");
        master.setBuyerAddress("华信智原");
        master.setBuyerOpenid("wx123456");
        master.setOrderAmount(new BigDecimal(2.3));

        System.out.println(master);

        OrderMaster result = masterRepository.save(master);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<OrderMaster> result = masterRepository.findByBuyerOpenid("ew3euwhd7sjw9diwkq", pageRequest);
        List<OrderMaster> masterList = result.getContent();
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}