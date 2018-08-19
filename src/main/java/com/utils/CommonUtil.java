package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;


public class CommonUtil {
	/**
	 * 计算两月份相差几个月
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static int countMonth(String start, String end) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(sdf.parse(end));
		aft.setTime(sdf.parse(start));
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		return Math.abs(month + result) + 1;
	}

	/**
	 * 计算时间间隔几天
	 * 
	 * @param startTime
	 *            起始时间
	 * @param endTime
	 *            终止时间
	 * @param format
	 *            时间格式：例如yyyyMMdd
	 * @return long
	 * @throws ParseException
	 */
	public static long countDays(String startTime, String endTime, String format) {
		long day = 0;
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			long hour = diff % nd / nh;// 计算差多少小时
			long min = diff % nd % nh / nm;// 计算差多少分钟
			long sec = diff % nd % nh % nm / ns;// 计算差多少秒
/*			if (sec >= 30) {
				min += 1;
			}
			if (min >= 30) {
				hour += 1;
			}*/
			if (hour >= 12) {
				day += 1;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 在月份上加上count个月
	 * 
	 * @param dateStr
	 * @param count
	 * @return
	 * @throws ParseException
	 */
	public static String addMonth(String dateStr, int count) throws ParseException {
		if (dateStr.length() == 6) {
			dateStr += "01";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMM");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf1.parse(dateStr);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, count);
		date = calendar.getTime();
		return sdf.format(date);
	}

	/**
	 * 
	 * @param dateStr
	 *            转换日期字符串
	 * @param fomatStrOld
	 * @param fomatStrNew
	 * @return 转换后的日期字符串
	 * @throws ParseException
	 */
	public static String fomatDate(String dateStr, String fomatStrOld, String fomatStrNew) throws ParseException {
		if (null==dateStr||"".equals(dateStr)) {
			return "";
		}

		SimpleDateFormat fomatStrOldSdf = new SimpleDateFormat(fomatStrOld);
		SimpleDateFormat fomatStrNewSdf = new SimpleDateFormat(fomatStrNew);
		return fomatStrNewSdf.format(fomatStrOldSdf.parse(dateStr));
	}


	/**
	 * 根据map中的可以获取value
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getMapStrVal(Map map, String key) {
		return map.get(key) == null ? "" : String.valueOf(map.get(key));
	}

	/**
	 * 获得当前格式时间
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String getCurrentTime(String dateFormat) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 获得当前时间加一天的时间戳
	 * 
	 * @return
	 */
	public static String getTomorrowDateTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天

		Date tomorrow = c.getTime();
		return f.format(tomorrow) + "000000";
	}

	/**
	 * 获得当前时间加一天的时间戳
	 * 
	 * @return
	 */
	public static String getNextMonthTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.MONTH, 1);// 今天+1月

		Date tomorrow = c.getTime();
		return f.format(tomorrow) + "000000";
	}

	/**
	 * 获取下个月的1日00：00：00
	 */
	public static String getNextMonthFirstDay() {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMM");
		Date today = new Date();
		String yearMonthStr = f.format(today);
		try {
			today = f.parse(yearMonthStr);
		} catch (ParseException e) {
		}
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.MONTH, 1);// +1月
		Date nextMonth = c.getTime();
		return f.format(nextMonth) + "01000000";
	}

	/**
	 * 计算相差对少年
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static int getCountYear(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		if ("".equals(dateStr)) {
			return date.getYear();
		}
		Date beforeDate = sdf.parse(dateStr);
		Long time = date.getTime() - beforeDate.getTime();
		return (int) ((time / (1000 * 24 * 60 * 60)) / 365);
	}
	
	/**
	 * 转换地市编码
	 */
	public static String getCityCode(String cityCode) throws ParseException {
		if(cityCode==null || "".equals(cityCode)) {
			return "";
		}
		if(cityCode.startsWith("0")) {
			cityCode = cityCode.substring(1);
			return cityCode;
		}
		return cityCode;
	}
	
	public static void main(String[] args) throws ParseException {
		//System.out.println(CommonUtil.getCityCode("0471"));
		
		//System.out.println(CommonUtil.countDays("2018-02-01 12:20:00","2018-02-03 12:20:00","YYYY-MM-DD hh:mm:ss"));
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate;
		Date endDate;
		beginDate = format.parse("2018-02-28 12:22:00");
		endDate= format.parse("2018-03-1 12:10:00");    
		long day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);  
		System.out.println(day);
	}
}
