package com.sunup.noend.service.impl;

import com.sunup.noend.constant.CommonConstant;
import com.sunup.noend.constant.OutboundOrderConstant;
import com.sunup.noend.entity.*;
import com.sunup.noend.mapper.CompanyMapper;
import com.sunup.noend.mapper.OrderMapper;
import com.sunup.noend.mapper.OutboundOrderMapper;
import com.sunup.noend.service.i.CommodityService;
import com.sunup.noend.service.i.OutboundOrderService;
import com.sunup.noend.util.RandomTools;
import com.sunup.noend.util.StringTools;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class OutboundOrderServiceImpl extends CommonServiceImpl<OutboundOrder> implements OutboundOrderService {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OutboundOrderMapper outboundOrderMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CommodityService commodityService;

    @Override
    public List<OutboundOrder> allByOrderNumber(String orderNumber) {
        return outboundOrderMapper.selectAllByOrderNumber(orderNumber);
    }

    @Override
    public int generateOrder(OutboundOrder outboundOrder, OutboundOrderParam param) throws BadHanyuPinyinOutputFormatCombination {
        int success = 0;
        //获取当前登录的用户
        User user = (User) request.getSession().getAttribute(CommonConstant.LOGIN_SUCCESS_USER_SESSION_KEY);
        final String orderNumber = generateOrderNumber();
        //设置出库单
        int is = param.getAmounts().indexOf(',');
        if (is > -1) {
            String[] amounts = param.getAmounts().split(",");
            String[] commodityIds = param.getCommodityIds().split(",");
            String[] lotNumbers = param.getLotNumbers().split(",");
            String[] norms = param.getNorms().split(",");
            String[] unitPrices = param.getUnitPrices().split(",");
            String[] commodityNames = param.getCommodityNames().split(",");

            List<OutboundOrder> outboundOrders = null;
            final int size = param.getAmounts().split(",").length;
            outboundOrders = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                outboundOrders.add(OutboundOrder.builder()
                        .status(OutboundOrderConstant.NOT_SETTLEMENT)
                        .createUserId(user.getId())
                        .createUsername(user.getAccountNumber())
                        .createTime(new Date())
                        .updateUserId(user.getId())
                        .updateUsername(user.getAccountNumber())
                        .updateTime(new Date())
                        .remark("")
                        .orderNumber(orderNumber)
                        .amount(Integer.parseInt(amounts[i]))
                        .commodityId(Integer.parseInt(commodityIds[i]))
                        .commodityLotNumber(lotNumbers[i])
                        .commodityName(commodityNames[i])
                        .commodityNorm(norms[i])
                        .unitPrice(Double.parseDouble(unitPrices[i]))
                        .build());
                //扣去库存
                Commodity commodity = commodityService.get(Integer.parseInt(commodityIds[i]));
                commodityService.update(Commodity.builder()
                        .id(Integer.parseInt(commodityIds[i]))
                        .inventory((commodity.getInventory() - Integer.parseInt(amounts[i])))
                        .build());
            }
            success = super.add(outboundOrders);
        } else {
            outboundOrder.setUnitPrice(Double.parseDouble(param.getUnitPrices()));
            outboundOrder.setCommodityNorm(param.getNorms());
            outboundOrder.setCommodityLotNumber(param.getLotNumbers());
            outboundOrder.setAmount(Integer.parseInt(param.getAmounts()));
            outboundOrder.setCommodityId(Integer.parseInt(param.getCommodityIds()));
            outboundOrder.setCommodityName(param.getCommodityNames());
            outboundOrder.setOrderNumber(orderNumber);
            success = super.add(outboundOrder);

            //扣去库存
            Commodity commodity = commodityService.get(Integer.parseInt(param.getCommodityIds()));
            commodityService.update(Commodity.builder()
                    .id(Integer.parseInt(param.getCommodityIds()))
                    .inventory((commodity.getInventory()- Integer.parseInt(param.getAmounts())))
                    .build());
        }

        if (success > 0) {
            Company company = companyMapper.selectByPrimaryKey(outboundOrder.getCompanyId());
            //计算总金额并保留两位小数
            double lumpSum = Double.parseDouble(String.format("%.2f", settlementAmount(orderNumber)));
            //生成订单
            success = orderMapper.insertSelective(Order.builder()
                    .orderNumber(orderNumber)
                    .lumpSum(lumpSum)
                    //将小写数字转为中文大写
                    .lumpSumChinese(StringTools.converNumberForChinese(lumpSum))
                    .receiptName(company.getName())
                    .receiptAddress(company.getAddress())
                    .receiptPhone(company.getPhone())
                    .createTime(new Date())
                    .createUserId(user.getId())
                    .createUsername(user.getUsername())
                    .updateTime(new Date())
                    .updateUserId(user.getId())
                    .updateUsername(user.getUsername())
                    .build());


        }

        return success;
    }

    @Override
    public Double settlementAmount(String orderNumber) {
        return outboundOrderMapper.selectAmountByOrderNumber(orderNumber);
    }


    @Override
    public String generateOrderNumber() {
        //初始编号长度20位  最后也应为20位
        final int length = 20;
        StringBuffer sb = new StringBuffer(length);
        sb.append(System.currentTimeMillis());
        sb.append(RandomTools.randInt(1000000, 9999999));
        return sb.toString();
    }

}
