package com.sunup.noend.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author Janly
 * @Description 汉字转拼音
 * @Date : Create in 10:43 2019/12/23
 */
@Slf4j
public class PingyinConver {

    /**
     * 汉字转拼音 字母全小写  去掉音标
     *
     * @param
     * @param firstLetter 如果  false则返回全拼音   为true返回拼音首字母
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static final String conver(final String str, boolean firstLetter) throws BadHanyuPinyinOutputFormatCombination {
        if (str.isEmpty()) {
            return "";
        }

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 控制大小写
        // UPPERCASE：大写  (ZHONG)
        // LOWERCASE：小写  (zhong)
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // WITHOUT_TONE：无音标  (zhong)
        // WITH_TONE_NUMBER：1-4数字表示英标  (zhong4)
        // WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);


        String regex = "[\u4e00-\u9fa5]+";

        //判断是否取首字母 否则取全拼音
        if (firstLetter) {
            StringBuffer sb = new StringBuffer();
            char[] arr = str.toCharArray();
            for (int i = 0, len = arr.length; i < len; i++) {
                if ((arr[i] + "").matches(regex)) {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], format)[0].charAt(0));
                }else{
                    sb.append(arr[i]);
                }
            }
            return sb.toString();
        } else {
            return PinyinHelper.toHanYuPinyinString(str, format, "", true);
        }
    }

    /**
     * 随机生成汉字
     */
    public static final String randomChar() throws UnsupportedEncodingException {
        String str = "";
        int highCode;
        int lowCode;
        Random random = new Random();
        //B0 + 0~39(16~55) 一级汉字所占区
        highCode = (176 + Math.abs(random.nextInt(39)));
        //A1 + 0~93 每区有94个汉字
        lowCode = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highCode)).byteValue();
        b[1] = (Integer.valueOf(lowCode)).byteValue();

        str = new String(b, "gbk");

        return str;
    }

    public static final String randomChar(int min, int max) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < RandomTools.randInt(min, max); i++) {
            sb.append(randomChar());
        }
        return sb.toString();
    }

    public static final List<String> randomChar(int min, int max, int length) throws UnsupportedEncodingException {
        List<String> strings = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            strings.add(randomChar(min, max));
        }
        return strings;
    }

//
//    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination, UnsupportedEncodingException {
//        log.info(conver("张磊",true));
////        log.info(JSON.toJSONString(randomChar(5,10,100000)));
//
//
//    }

}
