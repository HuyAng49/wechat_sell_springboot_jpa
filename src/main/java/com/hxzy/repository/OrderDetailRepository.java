package com.hxzy.repository;

import com.hxzy.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 21/5/20 下午7:41
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);

}
