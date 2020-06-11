package com.hxzy.service;

import com.hxzy.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {

    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void payOrder() {
    }

    @Test
    public void finishOrder() {
    }
}