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
public class Order {
    private Integer id;

    private String orderNumber;

    private Double lumpSum;
    private String lumpSumChinese;

    private String receiptAddress;

    private String receiptPhone;

    private String receiptName;

    private Integer status;

    private String createUsername;

    private Integer createUserId;

    private Date createTime;

    private String updateUsername;

    private Integer updateUserId;

    private Date updateTime;

    private String remark;

    private String senderAddress;

    private String senderPhone;

    private String senderName;


}