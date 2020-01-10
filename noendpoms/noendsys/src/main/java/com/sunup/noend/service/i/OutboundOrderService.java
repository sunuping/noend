package com.sunup.noend.service.i;

import com.sunup.noend.entity.OutboundOrder;
import com.sunup.noend.entity.OutboundOrderParam;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 23:50 2019/12/27
 */
public interface OutboundOrderService extends CommonService<OutboundOrder> {
    /**
     * 生成订单编号
     * @return
     */
    String generateOrderNumber();

    /**
     * 生成订单
     * @param outboundOrder
     * @param param
     * @return
     */
    int generateOrder(OutboundOrder outboundOrder, OutboundOrderParam param) throws BadHanyuPinyinOutputFormatCombination;

    /**
     * 结算金额
     * @param orderNumber
     * @return
     */
    Double settlementAmount(String orderNumber);

    /**
     * 根据订单编号查询
     * @param orderNumber
     * @return
     */
    List<OutboundOrder> allByOrderNumber(String orderNumber);
}
