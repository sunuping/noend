package com.sunup.noend.service.i;

import com.sunup.noend.entity.User;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 11:35 2019/12/3
 */
public interface UserService extends CommonService<User>{
    User getByAccountNumber(String accountNumber);
}
