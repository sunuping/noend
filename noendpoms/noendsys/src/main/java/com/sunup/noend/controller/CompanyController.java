package com.sunup.noend.controller;

import com.alibaba.fastjson.JSON;
import com.sunup.noend.entity.Company;
import com.sunup.noend.handler.exception.StringLengthLtThreeException;
import com.sunup.noend.pojo.Page;
import com.sunup.noend.pojo.Result;
import com.sunup.noend.service.i.CompanyService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 企业管理相关接口
 */
@Controller
@RequestMapping("/sales/company/")
@Slf4j
public class CompanyController {
    private final String path = "sales/company/";
    @Autowired
    private CompanyService companyService;

    /**
     * 出库管理
     *
     * @param model
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
    public Page list(Page page, @RequestParam(name = "search[value]", defaultValue = "") String searchValue) throws StringLengthLtThreeException{
        log.info(JSON.toJSONString(page));
        log.info(JSON.toJSONString(searchValue));

        page.setSearchValue(searchValue);
        Page temp = companyService.page(page);

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
    public Result add(Company c) throws BadHanyuPinyinOutputFormatCombination {
        int success = companyService.add(c);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @GetMapping("modify")
    public String modify(Model model, Integer id) {
        Company c = companyService.get(id);
        String temp = path + "modify";

        model.addAttribute("path", temp);
        model.addAttribute("company", c);
        return temp;
    }

    @PostMapping("modify")
    @ResponseBody
    public Result modify(Company c) throws BadHanyuPinyinOutputFormatCombination {
        int success = companyService.update(c);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }


    @PostMapping("del")
    @ResponseBody
    public Result del(Integer id) {
        int success = companyService.del(id);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }
}
