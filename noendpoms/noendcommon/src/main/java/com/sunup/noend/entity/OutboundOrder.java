package com.sunup.noend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutboundOrder {
    private Integer id;

    private String orderNumber;

    private Integer companyId;

    private Integer commodityId;

    private Integer status;

    private String createUsername;

    private Integer createUserId;

    private Date createTime;

    private String updateUsername;

    private Integer updateUserId;

    private Date updateTime;

    private String remark;

    private String companyName;

    private String commodityName;

    private String commodityNorm;

    private String commodityLotNumber;

    private Integer amount;

    private Double unitPrice;


    //commodity param


    private String commodityUnit;
    private String commodityValidUntil;
    private String commodityQuality;
    private String commodityHealthPermit;


}