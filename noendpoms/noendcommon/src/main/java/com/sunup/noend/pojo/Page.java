package com.sunup.noend.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 13:24 2019/12/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page {
    /**
     * 当前页
     */
    private int start;
    /**
     * 显示行数
     */
    private int length;
    /**
     * 过滤后的记录数
     */
    private int recordsFiltered;
    private Object data;
    private int draw;
    /**
     * 数据库里总共记录数
     */
    private int recordsTotal;
    /**
     * 动态参数
     */
    private String searchValue;


}
