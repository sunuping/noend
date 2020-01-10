package com.sunup.noend.mapper;

import com.sunup.noend.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends CommonMapper<User> {
    /**
     * 根据账号查询用户
     * @param accountNumber
     * @return
     */
    User selectByAccountNumber(String accountNumber);
}