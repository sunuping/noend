package com.sunup.noend.service.i;

import com.sunup.noend.entity.Company;
import com.sunup.noend.pojo.Page;

import java.util.List;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 10:21 2019/12/23
 */

public interface CompanyService extends CommonService<Company> {
    /**
     * 检索企业信息
     * @param searchValue
     * @return
     */
    List<Company> search(String searchValue);

    /**
     * 检查企业名称是否重复
     * @param name
     * @return
     */
    int checkCompanyNameRepeat(String name);

}
