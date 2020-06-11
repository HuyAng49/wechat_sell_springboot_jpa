package com.hxzy.controller;

import com.hxzy.pojo.ProductInfo;
import com.hxzy.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: huYang
 * @description: thy
 * @date: 2020-05-23 15:29
 */
@Controller
@RequestMapping("/product")
public class ThyController {

    @Autowired
    private ProductInfoService infoService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Sort sort = Sort.by(Direction.ASC, "productId");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<ProductInfo> infoList = infoService.findByPageable(pageable);
        model.addAttribute("list", infoList);
        return "list";
    }

}
