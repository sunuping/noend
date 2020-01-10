package com.sunup.noend.service.impl;

import com.sunup.noend.constant.CommonConstant;
import com.sunup.noend.entity.User;
import com.sunup.noend.service.i.SysUserDetailsService;
import com.sunup.noend.service.i.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 11:42 2019/12/10
 */
@Service
@Slf4j
public class SysUserDetailsServiceImpl implements SysUserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        User user = userService.getByAccountNumber(accountNumber);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }

        HttpSession session = request.getSession();
        session.setAttribute(CommonConstant.LOGIN_SUCCESS_USER_SESSION_KEY, user);
        session.setAttribute("success_user", accountNumber);

        List<GrantedAuthority> authorities = new ArrayList<>();
        //角色
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //权限
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        log.info("{}角色权限为：{}", accountNumber, authorities.toString());
        return new org.springframework.security.core.userdetails.User(user.getAccountNumber(), user.getPassword(),
                authorities);
    }

    public static void main(String[] args) {
        log.info(new BCryptPasswordEncoder().matches("admin","$2a$10$.7LJofWR4Eu1dlCF4nAokOpOTAh/t43OYAPFSEWGGRjuCYIiZ5P7.")+"");
        log.info(new BCryptPasswordEncoder().encode("admin"));
    }
}
