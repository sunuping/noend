package com.sunup.noend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commodity {
    private Integer id;

    private String name;
    private String namePinyin;
    private String namePinyinFirst;

    private String norm;

    private String lotNumber;

    private Integer inventory;

    private Double retailPrice;

    private Double supplyPrice;

    private Double wholesalePrice;

    private String createUsername;

    private Integer createUserId;

    private Date createTime;

    private String updateUsername;

    private Integer updateUserId;

    private Date updateTime;

    private String remark;

    private Integer amount;

    private String unit;

    private String healthPermit;

    private Date validUntil;

    private String quality;


}