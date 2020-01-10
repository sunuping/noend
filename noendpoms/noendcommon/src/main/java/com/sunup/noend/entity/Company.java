package com.sunup.noend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    private Integer id;

    private String name;

    private String namePinyin;

    private String namePinyinFirst;

    private String address;

    private String addressPinyin;

    private String addressPinyinFirst;

    private String uniformCreditCode;
    private String phone;
    private String landline;
    private String logo;

    private String relatedPictures;

    private String createUsername;

    private Integer createUserId;

    private Date createTime;

    private String updateUsername;

    private Integer updateUserId;

    private Date updateTime;

    private String remark;


}