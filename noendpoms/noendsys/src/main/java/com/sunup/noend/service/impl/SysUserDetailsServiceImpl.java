package com.sunup.noend.service.impl;

import com.sunup.noend.constant.CommonConstant;
import com.sunup.noend.entity.User;
import com.sunup.noend.service.i.SysUserDetailsService;
import com.sunup.noend.service.i.UserService;
import com.sunup.noend.util.VerificationImage;
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


@Service
@Slf4j
public class SysUserDetailsServiceImpl implements SysUserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        HttpSession session = request.getSession();

        //获取随机验证码
        String inputVerificationCode = request.getParameter("verificationCode");
        String verificationCode = session.getAttribute(VerificationImage.SESSION_KEY).toString();
        if (inputVerificationCode==null||!inputVerificationCode.equals(verificationCode)){
            throw new RuntimeException("验证码输入错误！");
        }


        User user = userService.getByAccountNumber(accountNumber);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }


        //


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
