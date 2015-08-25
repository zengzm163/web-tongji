package app.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import eu.bitwalker.useragentutils.Browser;

/**
 * 日期相关工具类
 * 

 * 
 */
public class DateUtil {
	
	/**
	 * 时间切分维度：小时
	 */
	public static final int DATE_SPILT_TYPE_HOUR =  1;
	
	/**
	 * 时间切分维度：天
	 */
	public static final int DATE_SPILT_TYPE_DAY =  2;
	
	/**
	 * 时间切分维度：周
	 */
	public static final int DATE_SPILT_TYPE_WEEK =  3;
	
	/**
	 * 时间切分维度：月
	 */
	public static final int DATE_SPILT_TYPE_MONTH = 4;
	
	/**
	 * 时间切分维度：季度
	 */
	public static final int DATE_SPILT_TYPE_QUARTER = 5;
	
	/**
	 * 时间切分维度：年
	 */
	public static final int DATE_SPILT_TYPE_YEAR = 6;



    /**
     * 将Date转化成long的秒。
     * 
     * @param date
     * @return
     */
	
	public static Integer parseDateToInt(Date date) {
		if (date == null) {
			return 0;
		}
		return (int)(date.getTime() / 1000);
	}
	
	/**
	 * unix时间戳转换成date
	 * @param unixTime
	 * @return
	 */
	public static Date unixTime2Date(int unixTime) {
		return new Date(Long.valueOf(unixTime)*1000);
	}
	
	/**
	 * 按天的维度进行日期切换，例如：
	 * 输入的day2Split=7，返回的结果是一个7个元素的列表，
	 * 每个元素代表从过去7天到当天的开始时间、结束时间，unxi时间戳
	 * 示例：[{1000,2000},{3000,4000},{5000,6000},{7000,8000}..............]
	 * @param day2Split
	 * @return
	 */
	public static List<Map<String, Integer>> split2Day(int day2Split) {
		Date currDate = new Date();
		List<Map<String, Integer>> list = new ArrayList<Map<String,Integer>>();
		for(int i=day2Split; i>=0; i--) {
			Date date = addDay(currDate, i*-1);
			String fromatDate = formatDateToString(date, "yyyy-MM-dd");
			String startTime = fromatDate + " 00:00:00";
			String endTime = fromatDate + " 23:59:59";
			//System.out.println("startTime："+startTime + "   endTime：" + endTime);
			Map<String, Integer> m = new HashMap<String, Integer>();
			m.put("startTime", parseDateToInt(formatStrToDate(startTime, "yyyy-MM-dd HH:mm:ss")));
			m.put("endTime", parseDateToInt(formatStrToDate(endTime, "yyyy-MM-dd HH:mm:ss")));
			list.add(m);
		}
		return list;
	}
	
	/**
	 * 将传入的时间切换分成00:00:00~23:59:59时间段
	 * @param date
	 * @return
	 */
	public static List<Map<String, Integer>> split2Hous(Date date) {
		List<Map<String, Integer>> list = new ArrayList<Map<String,Integer>>();
		String fromatDate = formatDateToString(date, "yyyy-MM-dd");
		String startTime = fromatDate + " 00:00:00";
		int start = parseDateToInt(formatStrToDate(startTime, "yyyy-MM-dd HH:mm:ss"));
		for(int i=1; i<=24; i++) {
			Map<String, Integer> m = new HashMap<String, Integer>();
			m.put("startTime", start);
			m.put("endTime", start + 3599);
			list.add(m);
			start = start + 3600;
		}
		return list;
	}
	
	/**
	 * 将一个时间段按指定维度（时、天、周、月、季度、年）进行切分，
	 * 返回的每个map为一个时间切片，里面包含startTime，endTime，unix时间戳
	 * @param startDate 
	 * @param endDate
	 * @param spiltType
	 * @return
	 */
	public static List<Map<String, Integer>> spiltDate(Date startDate, Date endDate, int spiltType) {
		List<Map<String, Integer>> list = new ArrayList<Map<String,Integer>>();
		String startDateFmt = formatDateToString(startDate, "yyyy-MM-dd") + " 00:00:00";
		String endDateFmt = formatDateToString(endDate, "yyyy-MM-dd") + " 23:59:59";
		Date newStratDate = formatStrToDate(startDateFmt, "yyyy-MM-dd hh:mm:ss");
		Date newEndDate = formatStrToDate(endDateFmt, "yyyy-MM-dd hh:mm:ss");
		int unixStartDate = parseDateToInt(newStratDate);
		int unixEndDate = parseDateToInt(newEndDate);
		if(spiltType == DATE_SPILT_TYPE_HOUR) {
			int start = unixStartDate;
			while(start < unixEndDate) {
				Map<String, Integer> m = new HashMap<String, Integer>();
				m.put("startTime", start);
				m.put("endTime", start + 3599);
				list.add(m);
				start = start + 3600;
			}
		} else if(spiltType == DATE_SPILT_TYPE_DAY) {
			int start = unixStartDate;
			while(start < unixEndDate) {
				Map<String, Integer> m = new HashMap<String, Integer>();
				m.put("startTime", start);
				m.put("endTime", start + (3600*24-1));
				list.add(m);
				start = start + 3600*24;
			}
		} else if(spiltType == DATE_SPILT_TYPE_WEEK) {
			
		} else if(spiltType == DATE_SPILT_TYPE_MONTH) {
			/*Calendar calendar = Calendar.getInstance();
			calendar.setTime(newStratDate);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);*/
		} else if(spiltType == DATE_SPILT_TYPE_QUARTER) {
			
		} else if(spiltType == DATE_SPILT_TYPE_YEAR) {
			
		}
		return list;
	}
	
	
	/**
     * 增加/减少天数
     * @param day 整数位加，负数为减
     * @return
     */
    public static Date addDay(Date date, int day) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.DAY_OF_YEAR, day);
    	return c.getTime();
    }
    
    /**
     * 将时间按格式转换为字符串，日期为空时转换为空字符。
     * 
     * @param date
     * @param patten
     * @return
     */
    public static String formatDateToString(Date date, String patten) {
        if (null == date){
            return "";
        }
        SimpleDateFormat sd = new SimpleDateFormat(patten);
        return sd.format(date);
    }
    
    /**
	 * 将字符串转换为指定的日期格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date formatStrToDate(String date, String format) {

		try {
			return getFormatter(format).parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取日期格式化对
	 * 
	 * @param format
	 * @return
	 */
	private static SimpleDateFormat getFormatter(String format) {
		return new SimpleDateFormat(format);
	}
	
	
	public static void main(String[] args) {
		//List<Map<String, Integer>> list = split2Day(30);
		/*List<Map<String, Integer>> list = split2Hous(new Date());
		System.out.println(list);
		
		System.out.println(formatDateToString(unixTime2Date(1438092000), "HH"));
		System.out.println(formatDateToString(unixTime2Date(1438092000), "yyyy/MM/dd"));*/
		/*List<Map<String, Integer>> list = spiltDate(formatStrToDate("2015-7-20", "yyyy-MM-dd"),  formatStrToDate("2015-7-30", "yyyy-MM-dd"), 2);
		System.out.println(list);*/
		int i = 0;
		for(Browser browser : Browser.values()) {
			
			System.out.println(i++ + " " +browser.getId() + "--" + browser.getName());
		}
		System.out.println((new Random()).nextInt()%400);
	}
}
