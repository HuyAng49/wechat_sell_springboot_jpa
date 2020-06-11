package com.hxzy.service.impl;

import com.hxzy.dto.CartDTO;
import com.hxzy.enums.ProductStatusEnum;
import com.hxzy.enums.ResultEnum;
import com.hxzy.exception.SellException;
import com.hxzy.pojo.ProductInfo;
import com.hxzy.repository.ProductInfoRepository;
import com.hxzy.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 21/5/20 下午1:42
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository infoRepository;

    @Override
    public ProductInfo findById(String infoId) {
        Optional<ProductInfo> infoOptional = infoRepository.findById(infoId);
        ProductInfo info = infoOptional.get();
        return info;
    }

    /**
     * 查询所有在架商品
     **/
    @Override
    public List<ProductInfo> findByOnAll() {
        return infoRepository.findByProductStatus(ProductStatusEnum.ON_SHELF.getCode());
    }

    @Override
    public Page<ProductInfo> findByPageable(Pageable pageable) {
        return infoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return infoRepository.save(productInfo);
    }

    /**
     * 加库存
     *
     * @param cartDTOList
     **/
    @Override
    @Transactional
    public ResultEnum increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cart : cartDTOList) {
            ProductInfo info = infoRepository.findById(cart.getProductId()).get();
            if (info == null) {
                log.error("【商品查询】商品不存在,productId={}", info.getProductId());
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = info.getProductStock() + cart.getProductQuantity();
            info.setProductStock(stock);
            ProductInfo updatedInfo = infoRepository.save(info);
            if (updatedInfo == null) {
                log.error("【库存更新】更新失败,productId={}", info.getProductId());
                throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
            }
        }
        return ResultEnum.SUCCSSES;
    }

    /**
     * 减库存
     *
     * @param cartDTOList
     **/
    @Override
    @Transactional
    public ResultEnum decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> infoOptional = infoRepository.findById(cartDTO.getProductId());
            ProductInfo info = infoOptional.get();

            if (info == null) {
                log.error("【商品查询】商品不存在,productId={}", info.getProductId());
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer stock = info.getProductStock() - cartDTO.getProductQuantity();
            if (stock < 0) {
                log.error("【商品查询】商品库存不足,productId={}", info.getProductId());
                throw new SellException(ResultEnum.PRODUCT_SHORTAGE);
            }

            info.setProductStock(stock);
            infoRepository.save(info);
        }
        return ResultEnum.SUCCSSES;
    }
}
