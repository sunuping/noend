package com.sunup.noend.mapper;

import com.sunup.noend.entity.OutboundOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OutboundOrderMapper extends CommonMapper<OutboundOrder>{

    /**
     * 查询统计出库单总额
     * @param orderNumber
     * @return
     */
    Double selectAmountByOrderNumber(String orderNumber);

    /**
     * 根据订单编号查询
     * @param orderNumber
     * @return
     */
    List<OutboundOrder> selectAllByOrderNumber(String orderNumber);

    /**
     * 删除待出库订单根据订单编号
     * @param orderNumber
     */
    int deleteByOrderNumber(String orderNumber);
}