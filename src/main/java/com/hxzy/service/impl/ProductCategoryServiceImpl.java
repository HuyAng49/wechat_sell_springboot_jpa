package com.hxzy.service.impl;

import com.hxzy.pojo.ProductCategory;
import com.hxzy.repository.ProductCategoryRepository;
import com.hxzy.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory findById(Integer categoryId) {
        Optional<ProductCategory> categoryOptional = categoryRepository.findById(categoryId);
        ProductCategory category = categoryOptional.get();
        return category;
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return categoryRepository.save(category);
    }
}
