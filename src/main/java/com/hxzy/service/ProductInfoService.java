package com.hxzy.service;

import com.hxzy.dto.CartDTO;
import com.hxzy.enums.ResultEnum;
import com.hxzy.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findById(String infoId);

    /**
     * 查询所有在架商品
     **/
    List<ProductInfo> findByOnAll();

    Page<ProductInfo> findByPageable(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     **/
    ResultEnum increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     **/
    ResultEnum decreaseStock(List<CartDTO> cartDTOList);

}
