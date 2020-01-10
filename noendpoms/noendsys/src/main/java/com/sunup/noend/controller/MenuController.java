package com.sunup.noend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 16:30 2019/12/11
 */
@Controller
@Slf4j
public class MenuController {
    /**
     * 跳转用户列表 需要权限 ROLE_USER
     * @return
     */
    @GetMapping("/sys/user/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN,ROLE_USER')")
    public String sysUserList(){
        return "sys/user/list";
    }
}
