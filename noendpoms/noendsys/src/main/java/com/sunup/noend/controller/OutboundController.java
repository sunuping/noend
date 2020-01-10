package com.sunup.noend.controller;

import com.sunup.noend.entity.Commodity;
import com.sunup.noend.entity.Company;
import com.sunup.noend.entity.OutboundOrder;
import com.sunup.noend.entity.OutboundOrderParam;
import com.sunup.noend.pojo.Page;
import com.sunup.noend.pojo.Result;
import com.sunup.noend.service.i.CommodityService;
import com.sunup.noend.service.i.CompanyService;
import com.sunup.noend.service.i.OutboundOrderService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * 临时订单管理相关接口
 */
@Controller
@RequestMapping("/sales/outbound/")
@Slf4j
public class OutboundController {
    private final String path = "sales/outbound/";

    @Autowired
    private CommodityService commodityService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private OutboundOrderService outboundOrderService;


    /**
     * 企业名称信息检索
     *
     * @param searchValue
     * @return
     */
    @PostMapping("company/search")
    @ResponseBody
    public Result companySearch(String searchValue) {
        if (searchValue == null || "".equals(searchValue.trim())) {
            return Result.error();
        }
        List<Company> companys = companyService.search(searchValue);
        return Result.success(companys);
    }

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
    public Page list(Page page) {
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
    public Result add(OutboundOrder outboundOrder,OutboundOrderParam outboundOrderParam) throws SQLException, BadHanyuPinyinOutputFormatCombination {
        int success = outboundOrderService.generateOrder(outboundOrder,outboundOrderParam);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @GetMapping("modify")
    public String modify(Model model, Integer id) {
        Commodity commodity = commodityService.get(id);
        String temp = path + "modify";

        model.addAttribute("path", temp);
        model.addAttribute("commodity", commodity);
        return temp;
    }

    @PostMapping("modify")
    @ResponseBody
    public Result modify(Commodity commodity) throws BadHanyuPinyinOutputFormatCombination {
        int success = commodityService.update(commodity);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }


    @PostMapping("del")
    @ResponseBody
    public Result del(Integer id) {
        int success = commodityService.del(id);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }
}
