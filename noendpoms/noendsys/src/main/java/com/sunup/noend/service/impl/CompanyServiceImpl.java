package com.sunup.noend.service.impl;

import com.sunup.noend.entity.Commodity;
import com.sunup.noend.entity.Company;
import com.sunup.noend.mapper.CompanyMapper;
import com.sunup.noend.pojo.Page;
import com.sunup.noend.service.i.CompanyService;
import com.sunup.noend.util.PingyinConver;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 10:22 2019/12/23
 */
@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class CompanyServiceImpl extends CommonServiceImpl<Company> implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int checkCompanyNameRepeat(String name) {
        return companyMapper.selectByName(name);
    }

    /**
     * 实现接口方法add 增加对名称及地址 的拼音及首字母 设置
     * @param company
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    @Override
    public int add(Company company) throws BadHanyuPinyinOutputFormatCombination {
        setPinyinAndFirstPinyin(company);
        return super.add(company);
    }

    /**
     * 实现接口方法update 增加对名称及地址 的拼音及首字母 设置
     * @param company
     * @return
     */
    @Override
    public int update(Company company) throws BadHanyuPinyinOutputFormatCombination {
        setPinyinAndFirstPinyin(company);
        return super.update(company);
    }

    @Override
    public List<Company> search(String searchValue) {
        //检测字符是否为中文 正则表达式
        String regex = "[\u4e00-\u9fa5]+";
        List<Company> companies = null;
        if (searchValue != null) {
            if (searchValue.matches(regex)) {
                companies = companyMapper.selectByNameOrAddress(searchValue);
            } else {
                companies = companyMapper.selectByNamePinyinFirstOrAddressPinyinFirst(searchValue);
                //如果按首字母查找为null 则继续按拼音查找
                if (companies == null || companies.size() == 0) {
                    companies = companyMapper.selectByNamePinyinOrAddressPinyin(searchValue);

                }
            }
        }
        return companies;
    }

    /**
     * 设置拼音 及 首字母
     * @param company
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private void setPinyinAndFirstPinyin(Company company) throws BadHanyuPinyinOutputFormatCombination {
        final String name = company.getName();
        final String address =company.getAddress();
        company.setNamePinyin(PingyinConver.conver(name,false));
        company.setNamePinyinFirst(PingyinConver.conver(name,true));
        company.setAddressPinyin(PingyinConver.conver(address,false));
        company.setAddressPinyinFirst(PingyinConver.conver(address,true));
    }
}
