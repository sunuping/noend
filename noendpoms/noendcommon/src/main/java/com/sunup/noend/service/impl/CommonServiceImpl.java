package com.sunup.noend.service.impl;

import com.sunup.noend.mapper.CommonMapper;
import com.sunup.noend.pojo.Page;
import com.sunup.noend.service.i.CommonService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 11:37 2019/12/3
 */
@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class CommonServiceImpl<T> implements CommonService<T> {

    @Autowired
    private CommonMapper<T> commonMapper;

    @Override
    public int add(T t) throws BadHanyuPinyinOutputFormatCombination {
        return commonMapper.insertSelective(t);
    }

    @Override
    public int add(List<T> objs) {
        return commonMapper.insertBatch(objs);
    }

    @Override
    public int del(int id) {
        return commonMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(T t) throws BadHanyuPinyinOutputFormatCombination {
        return commonMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public List<T> all() {
        return commonMapper.selectAll();
    }

    @Override
    public T get(int id) {
        return commonMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page page(Page page) {
        String searchValue = page.getSearchValue() == null ? "" : page.getSearchValue().trim().isEmpty() ? "" : page.getSearchValue().trim();

        log.info("相等查找");
        page = pageBySearch(page);
        if (searchValue.isEmpty() || page.getData() != null && page.getRecordsTotal() > 0) {
            return page;
        }

        //如果字符是中文或者是数字 执行字符检索 否则执行拼音或者首字母 全文检索
        if (isChinese(searchValue) || isNumber(searchValue)) {
            log.info("搜索中文");
            int count = commonMapper.selectCount(page);
            List<T> commodities = commonMapper.selectPage(page);
            page.setRecordsTotal(count);
            page.setRecordsFiltered(count);
            page.setData(commodities);
            return page;
        }

        //最后进行拼音查找
        return pageByPinyinSearch(page);

    }

    @Override
    public Page pageBySearch(Page page) {
        //相等查找
        List<T> commodities = commonMapper.selectPageBySearch(page);
        int count = commonMapper.selectCountBySearch(page);
        page.setRecordsTotal(count);
        page.setRecordsFiltered(count);
        page.setData(commodities);
        return page;
    }

    @Override
    public Page pageByPinyinSearch(Page page) {
        List<T> list = null;
        int count = 0;
        if (page.getData() == null || page.getRecordsTotal() < 1) {
            //首字母查找
            list = commonMapper.selectPageByPinyinFirstSearch(page);
            count = commonMapper.selectCountByPinyinFirstSearch(page);
            //全拼查找
            if (count < 1) {
                count = commonMapper.selectCountByPinyinSearch(page);
                list = commonMapper.selectPageByPinyinSearch(page);
            }
            page.setRecordsTotal(count);
            page.setRecordsFiltered(count);
            page.setData(list);
        }
        return page;
    }

    @Override
    public boolean isChinese(String searchValue) {
        //检测字符是否为中文 正则表达式
        String regex = "[\u4e00-\u9fa5]+";
        if ("".equals(searchValue)) {
            log.info("检测为空字符串");
            return true;
        } else if (searchValue.matches(regex)) {
            log.info("检测为中文");
            return true;
        }
        log.info("不是空字符串也不是中文");
        return false;
    }

    @Override
    public boolean isNumber(String searchValue) {
        String regex = "\\d*";
        return searchValue.matches(regex);
    }

}
