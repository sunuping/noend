package com.sunup.noend.service.i;

import com.sunup.noend.entity.OutboundOrder;
import com.sunup.noend.handler.exception.StringLengthLtThreeException;
import com.sunup.noend.pojo.Page;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 11:35 2019/12/3
 */
public interface CommonService<T> {
    int add(T t) throws BadHanyuPinyinOutputFormatCombination;

    int del(int id);

    int update(T t) throws BadHanyuPinyinOutputFormatCombination;

    List<T> all();

    T get(int id);

    /**
     * 分页
     *
     * @param page
     * @return
     */
    Page page(Page page);

    /**
     * 相等查找
     * @param page
     * @return
     */
    Page pageBySearch(Page page);

    /**
     * 搜索拼音分页
     *
     * @param page
     * @return
     */
    Page pageByPinyinSearch(Page page);

    /**
     * 检测字符是否为中文
     *
     * @param searchValue
     * @return
     */
    boolean isChinese(String searchValue);

    /**
     * 检测字符是否是数字
     * @param searchValue
     * @return
     */
    boolean isNumber(String searchValue);
    /**
     * 批量插入
     * @param objs
     * @return
     */
    int add(List<T> objs);
}
