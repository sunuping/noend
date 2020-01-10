package com.sunup.noend.entityz;

import java.util.Date;

public class Company {
    private Integer id;

    private String name;

    private String namePinyin;

    private String namePinyinFirst;

    private String address;

    private String addressPinyin;

    private String addressPinyinFirst;

    private String uniformCreditCode;

    private String logo;

    private String relatedPictures;

    private String createUsername;

    private Integer createUserId;

    private Date createTime;

    private String updateUsername;

    private Integer updateUserId;

    private Date updateTime;

    private String remark;

    private String phone;

    private String landline;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin == null ? null : namePinyin.trim();
    }

    public String getNamePinyinFirst() {
        return namePinyinFirst;
    }

    public void setNamePinyinFirst(String namePinyinFirst) {
        this.namePinyinFirst = namePinyinFirst == null ? null : namePinyinFirst.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddressPinyin() {
        return addressPinyin;
    }

    public void setAddressPinyin(String addressPinyin) {
        this.addressPinyin = addressPinyin == null ? null : addressPinyin.trim();
    }

    public String getAddressPinyinFirst() {
        return addressPinyinFirst;
    }

    public void setAddressPinyinFirst(String addressPinyinFirst) {
        this.addressPinyinFirst = addressPinyinFirst == null ? null : addressPinyinFirst.trim();
    }

    public String getUniformCreditCode() {
        return uniformCreditCode;
    }

    public void setUniformCreditCode(String uniformCreditCode) {
        this.uniformCreditCode = uniformCreditCode == null ? null : uniformCreditCode.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getRelatedPictures() {
        return relatedPictures;
    }

    public void setRelatedPictures(String relatedPictures) {
        this.relatedPictures = relatedPictures == null ? null : relatedPictures.trim();
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername == null ? null : createUsername.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername == null ? null : updateUsername.trim();
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline == null ? null : landline.trim();
    }
}