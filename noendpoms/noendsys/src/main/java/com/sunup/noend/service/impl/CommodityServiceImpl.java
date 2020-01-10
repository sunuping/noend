package com.sunup.noend.service.impl;

import com.sunup.noend.entity.Commodity;
import com.sunup.noend.entity.Company;
import com.sunup.noend.mapper.CommodityMapper;
import com.sunup.noend.pojo.Page;
import com.sunup.noend.service.i.CommodityService;
import com.sunup.noend.util.PingyinConver;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
public class CommodityServiceImpl extends CommonServiceImpl<Commodity> implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 修改商品 并设置拼音及首字母
     * @param commodity
     * @return
     */
    @Override
    public int update(Commodity commodity) throws BadHanyuPinyinOutputFormatCombination {
        setPinyinAndFirstPinyin(commodity);
        return super.update(commodity);
    }

    /**
     * 添加商品时 设置拼音 及 首字母
     * @param commodity
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    @Override
    public int add(Commodity commodity) throws BadHanyuPinyinOutputFormatCombination {
        //设置拼音 及 首字母
        setPinyinAndFirstPinyin(commodity);
        return super.add(commodity);
    }


    /**
     * 设置拼音 及 首字母
     * @param commodity
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private void setPinyinAndFirstPinyin(Commodity commodity) throws BadHanyuPinyinOutputFormatCombination {
        final String name = commodity.getName();
        commodity.setNamePinyin(PingyinConver.conver(name,false));
        commodity.setNamePinyinFirst(PingyinConver.conver(name,true));
    }
}
