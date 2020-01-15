package com.sunup.noend.controller;

import com.sunup.noend.util.VerificationImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@Slf4j
public class LoginController {

    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @GetMapping({"/","/index"})
    public String index() {
        return "index";
    }

    /**
     * 获取随机验证图片
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping({"/getVerificationImage"})
    @ResponseBody
    public void getVerificationImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Expire","0");
        response.setHeader("Pragma","no-cache");

        VerificationImage.getRandomCodeImage(request,response);
    }


}
