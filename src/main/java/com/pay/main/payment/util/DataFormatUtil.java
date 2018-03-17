package com.pay.main.payment.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 数据格式化工具类
 * 
 * @version 1.0
 * @author Guo
 */
public class DataFormatUtil {
	public static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	/**
	 * 生成制定前缀的32位随机数
	 * 
	 * @return
	 */
	public static String getONLYID32() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		StringBuffer sb = new StringBuffer("4070");
		Date currentTime = new Date();
		sb.append(formatter2.format(currentTime));
		sb.append(uuid.substring(0, 32 - sb.length()));
		return sb.toString();
	}
	
	public static String getCurrentTime(Integer index) {
		Date currentTime = new Date();
		String str = null;
		if (index == 1) {
			str = formatter2.format(currentTime);
		}
		return str;
	}
	
	/**
	 * 时间戳转换成Date
	 * 
	 * @param stamp
	 * @return
	 */
	public static Date convertTimestampToData(String stamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time = new Long(stamp) * 1000;
		String fd = sdf.format(time);
		Date liveDate = null;
		try {
			liveDate = sdf.parse(fd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return liveDate;
	}
	
	/**
	 * 获取时间
	 * 
	 * @param index 0减，1加
	 * @param hour 小时
	 * @return
	 */
	public static Date getDate(Integer index, Integer hour) {
		Calendar calendar = Calendar.getInstance();
		if (index == 0) {
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		} else if (index == 1) {
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
		}
		return calendar.getTime();
	}

    /**
     * 格式化当前日期，以字符串形式输出
     *
     * @param i 0:yyyyMMdd,1:HHmmss,2:yyyy-MM-dd HH:mm:ss,3:yyyy-MM-dd,4:HH:mm:ss,5:yyyyMMddHHmmss,default:yyyyMMddHHmmss
     * @param date 时间
     * @return
     */
    public static String currentDateFormat(int i, Date date) {
        String pattern = "";
        switch (i) {
            case 0:
                pattern = "yyyyMMdd";
                break;
            case 1:
                pattern = "HHmmss";
                break;
            case 2:
                pattern = "yyyy-MM-dd HH:mm:ss";
                break;
            case 3:
                pattern = "yyyy-MM-dd";
                break;
            case 4:
                pattern = "HH:mm:ss";
                break;
            case 5:
                pattern = "yyyyMMddHHmmss";
                break;
            default:
                pattern = "yyyyMMddHHmmss";
                break;
        }
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    
    public static String currentDateFormat(int i) {
    	return currentDateFormat(i, new Date());
    }
}