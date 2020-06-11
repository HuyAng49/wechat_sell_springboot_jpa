package com.hxzy.controller;

import com.hxzy.pojo.ProductCategory;
import com.hxzy.pojo.ProductInfo;
import com.hxzy.service.ProductCategoryService;
import com.hxzy.service.ProductInfoService;
import com.hxzy.transform.VoTransform;
import com.hxzy.util.ResultVoUtils;
import com.hxzy.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private ProductCategoryService categoryService;

    /** 1.查询所有上架商品 **/

    /** 2.查询类目（一次性查询） **/

    /**
     * 3.数据拼装
     **/

    @GetMapping("/vo")
    public ResultVo Vo() {

        /** 1.查询所有上架商品 **/
        List<ProductInfo> infoList = infoService.findByOnAll();

        /** 2.查询类目（一次性查询） 使用lambda表达式 **/
        List<Integer> categoryTypeList = infoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        return ResultVoUtils.success(VoTransform.Convert(infoList, categoryList));
    }

}
