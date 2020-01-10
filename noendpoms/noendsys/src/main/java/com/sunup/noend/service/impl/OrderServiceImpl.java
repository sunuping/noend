package com.sunup.noend.service.impl;

import com.sunup.noend.entity.Order;
import com.sunup.noend.entity.OutboundOrder;
import com.sunup.noend.mapper.OrderMapper;
import com.sunup.noend.mapper.OutboundOrderMapper;
import com.sunup.noend.service.i.CommonService;
import com.sunup.noend.service.i.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class OrderServiceImpl extends CommonServiceImpl<Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OutboundOrderMapper outboundOrderMapper;

    @Override
    public int delOrder(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        orderMapper.deleteByPrimaryKey(id);
        return outboundOrderMapper.deleteByOrderNumber(order.getOrderNumber());
    }
}
