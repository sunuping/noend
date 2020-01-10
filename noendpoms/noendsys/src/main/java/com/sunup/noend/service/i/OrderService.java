package com.sunup.noend.service.i;

import com.sunup.noend.entity.Order;
import io.swagger.annotations.ApiOperation;


public interface OrderService  extends CommonService<Order> {

    @ApiOperation("删除订单及临时订单")
    int delOrder(Integer id);
}
