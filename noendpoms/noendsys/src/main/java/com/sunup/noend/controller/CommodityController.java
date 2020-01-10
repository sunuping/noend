package com.sunup.noend.controller;

import com.alibaba.fastjson.JSON;
import com.sunup.noend.entity.Commodity;
import com.sunup.noend.handler.exception.StringLengthLtThreeException;
import com.sunup.noend.pojo.Page;
import com.sunup.noend.pojo.Result;
import com.sunup.noend.service.i.CommodityService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 14:39 2019/12/25
 */
@Controller
@RequestMapping("/sales/commodity/")
@Slf4j
public class CommodityController {
    private final String path = "sales/commodity/";
    @Autowired
    private CommodityService commodityService;

    /**
     * 商品库存列表
     *
     * @return
     */
    @GetMapping("list")
    public String list(Model model) {
        String temp = path + "list";
        model.addAttribute("path", temp);
        return temp;
    }

    @PostMapping("list")
    @ResponseBody
    public Page list(Page page,@RequestParam(name = "search[value]", defaultValue = "") String searchValue)throws StringLengthLtThreeException {
        page.setSearchValue(searchValue);
        Page temp = commodityService.page(page);
        return Page.builder().data(temp.getData())
                .draw(temp.getDraw())
                .recordsTotal(temp.getRecordsTotal())
                .recordsFiltered(temp.getRecordsFiltered())
                .build();
    }

    @GetMapping("add")
    public String add(Model model) {
        String temp = path + "add";
        model.addAttribute("path", temp);
        return temp;
    }

    @PostMapping("add")
    @ResponseBody
    public Result add(Commodity commodity) throws BadHanyuPinyinOutputFormatCombination {
        int success = commodityService.add(commodity);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @GetMapping("modify")
    public String modify(Model model,Integer id){
        Commodity commodity = commodityService.get(id);
        String temp = path + "modify";

        model.addAttribute("path", temp);
        model.addAttribute("commodity",commodity);
        return temp;
    }

    @PostMapping("modify")
    @ResponseBody
    public Result modify(Commodity commodity) throws BadHanyuPinyinOutputFormatCombination {
        int success = commodityService.update(commodity);
        if (success>0) {
            return Result.success();
        }
        return Result.error();
    }


    @PostMapping("del")
    @ResponseBody
    public Result del(Integer id){
        int success = commodityService.del(id);
        if (success>0) {
            return Result.success();
        }
        return Result.error();
    }
}
