package com.hxzy.repository;

import com.hxzy.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 21/5/20 下午7:41
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 用用户openid分页查询
     **/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
