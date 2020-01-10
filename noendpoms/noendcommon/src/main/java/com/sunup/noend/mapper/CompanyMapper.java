package com.sunup.noend.mapper;

import com.sunup.noend.entity.Company;
import com.sunup.noend.pojo.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompanyMapper extends CommonMapper<Company>{

    /**
     * 根据企业名称或者地址查询
     * @param company
     * @return
     */
    List<Company> selectByNameOrAddress(String company);

    /**
     * 根据企业名称拼音或者地址拼音查询
     * @param company
     * @return
     */
    List<Company> selectByNamePinyinOrAddressPinyin(String company);

    /**
     * 根据企业名称拼音首字母和地址拼音首字母查询
     * @param searchValue
     * @return
     */
    List<Company> selectByNamePinyinFirstOrAddressPinyinFirst(String searchValue);

    /**
     * 查询企业名称存在数目
     * @param name
     * @return
     */
    int selectByName(String name);
}