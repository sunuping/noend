package com.sunup.noend.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Janly
 * @Description
 * @Date : Create in 9:45 2019/12/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    /**
     * 失败
     */
    public static final int FAIL_CODE = 0;
    /**
     * 成功
     */
    public static final int SUCCESS_CODE = 1;

    private int code;
    private Object data;
    private String msg;

    public static Result error(int code, String msg) {
        return Result.builder().code(code).msg(msg).build();
    }
    public static Result error(){return Result.error(FAIL_CODE,"系统错误");}

    public static Result success() {
        return Result.builder().code(SUCCESS_CODE).msg("success").build();
    }

    public static Result success(Object data) {
        return Result.builder().code(SUCCESS_CODE).msg("success").data(data).build();
    }



}
