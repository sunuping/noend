package com.sunup.noend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.sunup.noend.mapper")
public class NoendsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoendsysApplication.class,args);
    }
}
