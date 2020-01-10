package com.sunup.noend.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringTools {

    public static final char[] NUMBER_BIG_WRITE = new char[]{'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
    public static final char[] INTEGER_BIT = new char[]{'元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿'};
    public static final char[] DECIMAL_BIT = new char[]{'分', '角'};
    public static final StringBuffer BUFFER = new StringBuffer();
    public static final StringBuilder BUILDER = new StringBuilder();

    /**
     * 将小写数字转换为中文大写
     *
     * @param number
     * @return
     */
    public static String converNumberForChinese(double number) {
        String numberStr = number + "";
        int index = numberStr.indexOf('.');

        int number1 = Integer.parseInt(numberStr.substring(0, index));
        int number2 = Integer.parseInt(numberStr.substring(index+1));
        log.info(number + "");

        return converNumberForChineseMax(number1) + converNumberForChineseMin(number2);
    }

    /**
     * 4651245 结果 肆佰陆拾伍万壹仟贰佰肆拾伍元
     * @param number
     * @return
     */
    public static String converNumberForChineseMax(int number) {
        BUFFER.delete(0, BUFFER.length());
        int unit = 0;
        int num = 0;
        while (number !=0) {
            BUFFER.insert(0, INTEGER_BIT[unit++]);
            num = (int) (number % 10);
            BUFFER.insert(0, NUMBER_BIG_WRITE[num]);
            number = number / 10;
        }
        return BUFFER.toString();
    }

    /**
     * 02  结果 贰分
     * @param number
     * @return
     */
    public static String converNumberForChineseMin(int number) {
        BUFFER.delete(0, BUFFER.length());
        int unit = 0;
        int num = 0;
        while (number !=0) {
            BUFFER.insert(0, DECIMAL_BIT[unit++]);
            num = (int) (number % 10);
            BUFFER.insert(0, NUMBER_BIG_WRITE[num]);
            number = number / 10;
        }
        return BUFFER.toString();
    }

    public static void main(String[] args) {

        log.info(converNumberForChinese(1651245));
        log.info(converNumberForChinese(1651245.021));
        log.info(converNumberForChinese(1651245.3));
        log.info(converNumberForChinese(1651245.22));
        log.info(converNumberForChinese(1651245.0));

    }

    /**
     * 生成随机字符串
     *
     * @return
     */
    public static String generateRandomString() {
        BUFFER.delete(0, BUFFER.length());
        final String s = "poiuytrewqasdfghjklmnbvcxz0987654321";
        final int slen = s.length() - 1;
        for (int i = 0, len = 16; i < len; i++) {
            BUFFER.append(s.charAt(RandomTools.randInt(0, slen)));
        }
        return BUFFER.toString();
    }

    /**
     * 对象拼接
     *
     * @param params
     * @return StringBuilder
     */
    public static StringBuilder builderAppend(Object... params) {
        BUFFER.delete(0, BUFFER.length());
        for (int i = 0, len = params.length; i < len; i++) {
            BUFFER.append(params[i]);
        }
        return BUILDER;
    }

    /**
     * 对象拼接
     *
     * @param params
     * @return String
     */
    public static String builderAppendToString(Object... params) {
        return builderAppend(params).toString();
    }

    /**
     * 对象拼接
     *
     * @param params
     * @return StringBuffer
     */
    public static StringBuffer bufferAppend(Object... params) {
        BUFFER.delete(0, BUFFER.length());
        for (int i = 0, len = params.length; i < len; i++) {
            BUFFER.append(params[i]);
        }

        return BUFFER;
    }

    /**
     * 对象拼接
     *
     * @param params
     * @return String
     */
    public static String bufferAppendToString(Object... params) {
        return bufferAppend(params).toString();
    }


}
