package com.sunup.noend.handler;

import com.sunup.noend.handler.exception.IncorrectCredentialsException;
import com.sunup.noend.handler.exception.StringLengthLtThreeException;
import com.sunup.noend.handler.exception.UnknownAccountException;
import com.sunup.noend.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Janly
 * @Description 约定 code 为 0 表示操作成功, 1 或 2 等正数表示用户输入错误, -1, -2 等负数表示系统错误.
 * @Date : Create in 10:03 2019/12/4
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class ApiExceptionHandler {
    @ExceptionHandler
    public  Result stringLengthLtThree(StringLengthLtThreeException e) {
        String msg = "字符数小于3个";
        log.error(msg, e);
        return Result.error(2, msg);
    }

    @ExceptionHandler
    public  Result unknownAccount(UnknownAccountException e) {
        String msg = "账号不存在";
        log.error(msg, e);
        return Result.error(1, msg);
    }

    @ExceptionHandler
    public  Result incorrectCredentials(IncorrectCredentialsException e) {
        String msg = "密码错误";
        log.error(msg, e);
        return Result.error(-2, msg);
    }

    @ExceptionHandler
    public Result unknownException(Exception e) {
        String msg = "未知异常";
        log.error(msg, e);
        return Result.error(-99, msg);
    }

}
