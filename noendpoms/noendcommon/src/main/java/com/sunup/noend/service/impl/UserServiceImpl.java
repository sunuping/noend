package com.sunup.noend.service.impl;

import com.sunup.noend.entity.User;
import com.sunup.noend.mapper.UserMapper;
import com.sunup.noend.service.i.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends CommonServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getByAccountNumber(String accountNumber) {
        return userMapper.selectByAccountNumber(accountNumber);
    }
}
