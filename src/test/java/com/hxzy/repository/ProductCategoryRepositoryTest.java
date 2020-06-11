package com.hxzy.repository;

import com.hxzy.pojo.ProductCategory;
import com.hxzy.service.ProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryService repository;

    @Test
    public void findByIdTest() {
        ProductCategory category = repository.findById(1);
        System.out.println(category);
    }

    @Test
    public void saveTest() {
        ProductCategory category = repository.findById(4);
        category.setCategoryName("女生都爱");
        repository.save(category);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(123456, 123123, 121212, 3);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}