package com.sunup.noend.controller;

import com.sunup.noend.entity.Order;
import com.sunup.noend.entity.OutboundOrder;
import com.sunup.noend.pojo.Page;
import com.sunup.noend.pojo.Result;
import com.sunup.noend.service.i.OrderService;
import com.sunup.noend.service.i.OutboundOrderService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 订单管理相关接口
 */
@Controller
@RequestMapping("/sales/order/")
@Slf4j
public class OrderController {
    private final String path = "sales/order/";
    @Autowired
    private OrderService orderService;
    @Autowired
    private OutboundOrderService outboundOrderService;

    @GetMapping("print-preview")
    public String preview(int id, Model model) {
        String temp = path + "print-preview";
        setPrintParams(id, model, temp);
        return temp;
    }

    @GetMapping("print")
    public String print(int id, Model model) {
        String temp = path + "print";
        setPrintParams(id, model, temp);
        return temp;
    }

    /**
     * 设置打印数据
     * @param id
     * @param model
     * @param temp
     */
    private void setPrintParams(int id, Model model, String temp) {
        Order order = orderService.get(id);
        List<OutboundOrder> outboundOrders = outboundOrderService.allByOrderNumber(order.getOrderNumber());
        model.addAttribute("path", temp);
        model.addAttribute("order", order);
        model.addAttribute("outboundOrders", outboundOrders);
        model.addAttribute("outboundOrderSize", outboundOrders.size());
    }

    @GetMapping("list")
    public String list(Model model) {
        String temp = path + "list";
        model.addAttribute("path", temp);
        return temp;
    }

    @PostMapping("list")
    @ResponseBody
    public Page list(Page page, @RequestParam(name = "search[value]", defaultValue = "") String searchValue) {
        page.setSearchValue(searchValue);
        Page temp = orderService.page(page);
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
    public Result add(Order order) throws SQLException, BadHanyuPinyinOutputFormatCombination {
        int success = orderService.add(order);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @GetMapping("modify")
    public String modify(Model model, Integer id) {
        Order order = orderService.get(id);
        String temp = path + "modify";

        model.addAttribute("path", temp);
        model.addAttribute("order", order);
        return temp;
    }

    @PostMapping("modify")
    @ResponseBody
    public Result modify(Order order) throws BadHanyuPinyinOutputFormatCombination {
        int success = orderService.update(order);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }


    @PostMapping("del")
    @ResponseBody
    public Result del(Integer id) {
        int success = orderService.delOrder(id);
        if (success > 0) {
            return Result.success();
        }
        return Result.error();
    }
}
