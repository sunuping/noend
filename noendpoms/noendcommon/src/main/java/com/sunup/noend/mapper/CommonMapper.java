package com.sunup.noend.mapper;

import com.sunup.noend.entity.Commodity;
import com.sunup.noend.entity.Company;
import com.sunup.noend.pojo.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 11:32 2019/12/3
 */
@Component
public interface CommonMapper<T> {
    int deleteByPrimaryKey(Integer id);

     int insert(T t);

     int insertSelective(T t);

     T selectByPrimaryKey(Integer id);

     int updateByPrimaryKeySelective(T t);

     int updateByPrimaryKey(T t);

     List<T> selectAll();

     int selectCount(Page page);

     List<T> selectPage(Page page);

     /**
     * 拼音搜索分页
     * @param page
     * @return
     */
    List<T> selectPageByPinyinSearch(Page page);

    /**
     * 拼音首字母搜索分页
     * @param page
     * @return
     */
    List<T> selectPageByPinyinFirstSearch(Page page);
    /**
     * 拼音搜索分页统计
     * @param page
     * @return
     */
    int selectCountByPinyinSearch(Page page);
    /**
     * 拼音首字母搜索分页统计
     * @param page
     * @return
     */
    int selectCountByPinyinFirstSearch(Page page);

    /**
     * 查询相等的值
     * @param page
     * @return
     */
    List<T> selectPageBySearch(Page page);

    /**
     * 查询相等的值的统计
     * @param page
     * @return
     */
    int selectCountBySearch(Page page);

    /**
     * 批量插入
     * @param objs
     * @return
     */
    int insertBatch(@Param("objs") List<T> objs);
}
