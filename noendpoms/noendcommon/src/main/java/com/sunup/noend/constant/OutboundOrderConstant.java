package com.sunup.noend.constant;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 1:04 2019/12/28
 */
public class OutboundOrderConstant {
    /**
     * 0未结算
     */
    public static final int NOT_SETTLEMENT = 0;
    /**
     *  1已结算未发货
     */
    public static final int ALREADY_SETTLEMENT_NOT_SHIP = 1;
    /**
     *  2未结算已发货
     */
    public static final int NOT_SETTLEMENT_ALREADY_SHIP = 2;

}
