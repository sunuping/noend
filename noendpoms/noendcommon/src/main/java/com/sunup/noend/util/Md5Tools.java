package com.sunup.noend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lime
 */
public class Md5Tools {
    private static Logger logger = LoggerFactory.getLogger(Md5Tools.class);

    public static String encry(String content) {
        MessageDigest m;
        String result = "";
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] s = m.digest();
            for (int i = 0, len = s.length; i < len; i++) {
                result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        PrintTools.print(Md5Tools.encry("123123"));
//    }


}
