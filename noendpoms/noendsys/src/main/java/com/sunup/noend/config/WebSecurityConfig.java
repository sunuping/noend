package com.sunup.noend.config;

import com.sunup.noend.service.i.SysUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 11:23 2019/12/10
 * @EnableWebSecurity注解开启Spring Security的功能
 * 使用@EnableGlobalMethodSecurity(prePostEnabled = true)这个注解，可以开启security的注解，我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysUserDetailsService userDetailsService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 定制请求的授权规则
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //cors解决跨域问题
        http.cors().and()
                //关闭跨站请求伪造
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/static/**","/css/**", "/js/**", "/assets/**", "/googleapis/**", "/libs/**", "/favicon.png").permitAll()
                //其他登录后访问
                .antMatchers("/**").hasRole("ADMIN");
        // 解决不允许显示在iframe的问题
        http.headers().frameOptions().disable();
        //开启自动配置的登录功能
        http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        //开启自动配置的注销功能，会访问/logout请求 //注销成功后，回到首页
        http.logout().logoutSuccessUrl("/");
        /*开启记住我功能（开启后,springboot会给浏览器发送一个cookies,以后访问网站都会带上这个cookies给springboot验证,springboot会检查以前某一个用户的cookies
        的值是什么,如果找到了,这个用户就不用再次登录了,注销时候springboot会发送命令给浏览器删除cookies）*/
        http.rememberMe();
        http.csrf().disable();

    }

    /**
     * 定义认证规则
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用自定义认证规则，并且使用BCrypt算法处理密码
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
