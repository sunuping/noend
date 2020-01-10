package com.sunup.noend.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateTools {
    public static final String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static final Date getDateByString(String time) throws Exception {
        return new SimpleDateFormat().parse(time);
    }

    public static final int getCurrentMonthDayCount(int year, int month){
            int days = 0;
            if (month != 2) {
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        days = 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        days = 30;

                }
            } else {
                // 闰年
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    days = 29;
                } else {
                    days = 28;
                }
            }
            return days;

    }


    public static final Date addDate(int calendarType, int i) {
        Calendar calendar = Calendar.getInstance();

        switch (calendarType) {
            case Calendar.SECOND:
                calendar.add(Calendar.SECOND, i);
                break;
            case Calendar.YEAR:
                calendar.add(Calendar.YEAR, i);
                break;
            case Calendar.MONTH:
                calendar.add(Calendar.MONTH, i);
                break;
            default:
                break;
        }

        return calendar.getTime();
    }

//    public static void main(String[] args) throws Exception {
////        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
////        Date d1 = df.parse("2020-02-04");
////        Date d2 = new Date();
////        long diff = d1.getTime() - d2.getTime();
////        log.debug(diff / 1000 / 60 / 60 / 24 + "");
////        log.debug(diff / 1000 / 60 / 60 / 24 / 30 + "");
//        log.info(getCurrentMonthDayCount(2019,7)+"");
//    }
}
