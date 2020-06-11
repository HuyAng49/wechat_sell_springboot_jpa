package com.hxzy.transform;

import com.hxzy.pojo.ProductCategory;
import com.hxzy.pojo.ProductInfo;
import com.hxzy.vo.ProductInfoVo;
import com.hxzy.vo.ProductInfo_Category_Vo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class VoTransform {

    /**
     * 数据拼装
     *
     * @param infoList
     * @param categoryList
     * @return java.util.List<com.hxzy.vo.ProductInfo_Category_Vo>
     * @author huYang
     * @creed: Talk is cheap,show me the code
     * @date 21/5/20 下午6:34
     */
    public static List<ProductInfo_Category_Vo> Convert(List<ProductInfo> infoList, List<ProductCategory> categoryList) {

        List<ProductInfo_Category_Vo> info_category_VoList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            ProductInfo_Category_Vo info_category_vo = new ProductInfo_Category_Vo();
            info_category_vo.setCategoryName(category.getCategoryName());
            info_category_vo.setCategoryType(category.getCategoryType());

            List<ProductInfoVo> infoVoList = new ArrayList<>();
            for (ProductInfo info : infoList) {
                if (category.getCategoryType().equals(info.getCategoryType())) {
                    ProductInfoVo infoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(info, infoVo);
                    infoVoList.add(infoVo);
                }
            }

            info_category_vo.setProductInfoVoList(infoVoList);
            info_category_VoList.add(info_category_vo);

        }
        return info_category_VoList;
    }
}
