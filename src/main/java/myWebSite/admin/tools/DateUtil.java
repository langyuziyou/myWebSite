package myWebSite.admin.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
/**
 * 时间日期工具类
 * 
 * @author pengyong
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils{
	
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public final static String FORMAT_YYYYMMDD = "yyyyMMdd";
	public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyyMMdd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 日期型字符串转化为日期 格式-获取起始值
	 * { "yyyy-MM-dd"}
	 */
	public static Date getDateStart(String str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str+" 00:00:00", parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 日期型字符串转化为日期 格式-获取结束值
	 * { "yyyy-MM-dd"}
	 */
	public static Date getDateEnd(String str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str+" 23:59:59", parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	/**
	 * 得到当前月份字符串英文格式（MMMM）
	 */
	public static String getEMonth() {
		 SimpleDateFormat sdf = new SimpleDateFormat("MMMM",  
	                Locale.ENGLISH);  
		return sdf.format(new Date());
	}

	
	/**
	 * 获取昨天日期
	 * @return
	 */
	public static Date getyesterdayDate() {
		  Calendar   cal   =   Calendar.getInstance();
		  cal.add(Calendar.DATE,   -1);
		  String yesterday = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss ").format(cal.getTime());
		  System.out.println(yesterday);
		  return cal.getTime();
	}
	
	
	
	/**
	 * 获取昨天日期
	 * @return
	 */
	public static String getyesterdayStr() {
		  Calendar   cal   =   Calendar.getInstance();
		  cal.add(Calendar.DATE,   -1);
		  String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
		  System.out.println(yesterday);
		  return yesterday;
	}
	/**
	 * 获取明天日期
	 * @return
	 */
	public static String getTomorrowStr() {
		  Calendar   cal   =   Calendar.getInstance();
		  cal.add(Calendar.DATE,   1);
		  String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
		  System.out.println(yesterday);
		  return yesterday;
	}
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String getSysCurDateTime() {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result=sf.format(new Date());
		return result;
	}
	/**
	 * 格式化时间
	 * @return
	 */
	public static String formatDateToStr(String fomat,Date date) {
		if(date == null){
			return "";
		}
		SimpleDateFormat sf=new SimpleDateFormat(fomat);
		String result=sf.format(date);
		return result;
	}
	/**
	 * 获取系统当前时间数字形式
	 * @return
	 */
	public static String getNumberDateTime() {
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddHHmmss");
		String result=sf.format(new Date());
		return result;
	}
	
	/**
	 * 获取系统当前时间发短信用格式
	 * @return
	 */
	public static String getSmsDate() {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd");
		String result=sf.format(new Date());
		return result;
	}
	
	/**
	 * 获取系统当前日期
	 * @return
	 */
	public static String getSysCurFmtDate() {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String result=sf.format(new Date());
		return result;
	}
	
	
	public static Map getCurrTimeInfo(){
		Map timeMap = new HashMap();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//获取年份
		int month=cal.get(Calendar.MONTH) + 1;//获取月份
		int day=cal.get(Calendar.DATE);//获取日
		int hour=cal.get(Calendar.HOUR_OF_DAY);//24小时
		int minute=cal.get(Calendar.MINUTE);//
		int second=cal.get(Calendar.SECOND);//秒
		//System.out.println("现在的时间是：公元"+year+"年"+month+"月"+day+"日? "+hour+"时"+minute+"分"+second+"秒?? 星期"+WeekOfYear);
		timeMap.put("year", year);
		timeMap.put("month", month);
		timeMap.put("day", day);
		timeMap.put("hour", hour);
		timeMap.put("minute", minute);
		timeMap.put("second", second);
		return timeMap;
	}
	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay(Date date) {
		return sdfDay.format(date);
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(Date date) {
		return sdfDays.format(date);
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime(Date date) {
		return sdfTime.format(date);
	}
	
	/**
     * 根据字符串格式去转换相应格式的日期和时间
     * **/
    public static Date reverseDate(String date) {
        SimpleDateFormat simple = null;
        switch (date.trim().length()) {
        case 21:// 日期+时间+毫秒
        	 simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             break;
        case 19:// 日期+时间
            simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            break;
        case 14:// 日期+时间
            simple = new SimpleDateFormat("yyyyMMddHHmmss");
            break;
        case 10:// 仅日期
            simple = new SimpleDateFormat("yyyy-MM-dd");
            break;
        case 8:// 仅时间
            simple = new SimpleDateFormat("HH:mm:ss");
            break;
        default:
            break;
        }
        try {
            return simple.parse(date.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 得到某年某月的第一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
    
     Calendar cal = Calendar.getInstance();
     cal.set(Calendar.YEAR, year);
     cal.set(Calendar.MONTH, month-1);
     cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
     cal.set(Calendar.HOUR_OF_DAY, 0);
     cal.set(Calendar.MINUTE, 0);
     cal.set(Calendar.SECOND, 0);
    
     return cal.getTime();
    }
    
    /**
     * 得到某年某月的最后一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
    
     Calendar cal = Calendar.getInstance();
     cal.set(Calendar.YEAR, year);
     cal.set(Calendar.MONTH, month-1);
     cal.set(Calendar.DAY_OF_MONTH,  cal.getActualMaximum(Calendar.DAY_OF_MONTH));
     cal.set(Calendar.HOUR_OF_DAY, 23);
     cal.set(Calendar.MINUTE, 59);
     cal.set(Calendar.SECOND, 59);
    
     return cal.getTime();
    }
    
    
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getSomeYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }
    
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getSomeYearLast(int year){  
        Calendar cal = Calendar.getInstance();  
        cal.clear();  
        cal.set(Calendar.YEAR, year); 
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date currYearFirst = cal.getTime();  
        return currYearFirst;  
    }
    /*** 
     * 两个日期相差多少秒 
     *  
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getTimeDelta(Date date1,Date date2){  
        long timeDelta=(date1.getTime()-date2.getTime())/1000;//单位是秒  
        int secondsDelta=timeDelta>0?(int)timeDelta:(int)Math.abs(timeDelta);  
        return secondsDelta;  
    }  
    
    /**
     * 获得两个日期相差的天数
     * @param early
     * @param late
     * @return
     */
    public static  int daysBetween(Date early, Date late) { 
        
        Calendar calst = Calendar.getInstance();   
        Calendar caled = Calendar.getInstance();   
        calst.setTime(early);   
        caled.setTime(late);   
         //设置时间为0时   
         calst.set(Calendar.HOUR_OF_DAY, 0);   
         calst.set(Calendar.MINUTE, 0);   
         calst.set(Calendar.SECOND, 0);   
         caled.set(Calendar.HOUR_OF_DAY, 0);   
         caled.set(Calendar.MINUTE, 0);   
         caled.set(Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }
    
    /**
     * 日期加减几天
     * @param date
     * @param days
     * @return
     */
    public static  Date addDays(Date date,int days) { 
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_MONTH, days);
    	return calendar.getTime();
    }
    
    /**
     * 日期加减几个月
     * @param date
     * @param months
     * @return
     */
    public static  Date addMonths(Date date,int months) { 
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.MONTH, months);
    	return calendar.getTime();
    }
    /**
     * 日期加减几个月
     * @param date
     * @param months
     * @return
     */
    public static  Date addYears(Date date,int years) { 
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.YEAR, years);
    	return calendar.getTime();
    }
    /**
	 * 获取YYYY年MM月DD日格式
	 * 
	 * @return
	 */
	public static String getFmtChineseDate(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		return sf.format(date);
	}
	

	 /**
		 * 获取YYYY-MM-DD格式
		 * 
		 * @return
		 */
		public static String getFmtDate(Date date) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			return sf.format(date);
		}
		/**
		 * 获取YYYY-MM-DD格式
		 * 
		 * @return
		 */
		public static Date getFmtDate(Date date, String fmt) {
			SimpleDateFormat sf = new SimpleDateFormat(fmt);
			String dateStr = sf.format(date);
			Date rsDate = null;
			try {
				rsDate = sf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return rsDate;
		}
		/**
		* 字符串转换成日期
		* @param str
		* @return date
		*/
		public static Date StrToDate(String str,String formatStr) {
		  
		   SimpleDateFormat format = new SimpleDateFormat(formatStr);
		   Date date = null;
		   try {
		    date = format.parse(str);
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		   return date;
		}
		
		/**2015-07-31 14:56:09   变Date*/
		public static Date stringToDate(String date){
			if(null == date){
				return null;
			}
			try{
				if(date.length()>10){
					date = date.substring(0, 19);
					return sdfTime.parse(date);
				}
				return sdfDay.parse(date);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * date转jodatime
		 * @param date
		 * @return
		 */
/*		public static DateTime toJodaDateTime(Date date) {
			if(date==null)
				return DateTime.now();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sf.format(date);
			DateTimeFormatter formatter = DateTimeFormat
					.forPattern("yyyy-MM-dd HH:mm:ss");
			DateTime dt = formatter.parseDateTime(dateStr);
			return dt;
		}*/
		
		/**
	     * 日期加减几天
	     * @param date
	     * @param days
	     * @return
	     */
	    public static  Date addDaysNew(Date date,int days) { 
	    	Calendar calendar=Calendar.getInstance();
	    	calendar.setTime(date);
	    	calendar.add(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+days);
	    	return calendar.getTime();
	    }
	    
	    /**
	     * 日期加减几分钟
	     * @param date
	     * @param minutes
	     * @return
	     */
	    public static  Date addMinutes(Date date,int minutes) { 
	    	Calendar calendar=Calendar.getInstance();
	    	calendar.setTime(date);
	    	calendar.add(Calendar.MINUTE, minutes);
	    	return calendar.getTime();
	    }
        
	    /**
	     * 获取当前时间
	     * @return
	     */
	    public static Date getCurrTime(){
	    	Calendar calendar=Calendar.getInstance();
	    	calendar.set(Calendar.HOUR_OF_DAY, 0);
	    	calendar.set(Calendar.MINUTE, 0);
	    	calendar.set(Calendar.SECOND, 0);
	    	calendar.set(Calendar.MILLISECOND, 0);
	    	return calendar.getTime();
	    }
	    
	    /**
	     * 获取组装后的时间
	     * @return
	     */
	    public static Date getAssembleTime(Date date,String str){
	    	String assembleTime = getDay(date)+str;
	    	return reverseDate(assembleTime);
	    }
	    /**
	     * 判断时间是否超过24小时
	     */
	    public static boolean compareTime(Date time1,Date time2){
	    	if(time1==null||time2==null)
	    		return false;
	    	long diff=time2.getTime()-time1.getTime();
	    	if(diff<0){
	    		return false;
	    	}
	    	double result=diff*1.0/(1000*60*60);
	    	if(result<=24)
	    		return true;
	    	else
	    		return false;
	    }
	    
	    public static Long getDateLong() {
	    	Date dt= new Date();
	    	Long time= dt.getTime();//这就是距离1970年1月1日0点0分0秒的毫秒数
	    	System.out.println(System.currentTimeMillis());//与上面的相同
	    	return time;
    	}
	    
	    /**
		 * 获取YYYY-MM-DD格式
		 * 
		 * @return
		 */
		public static String getFmtDateHMS(Date date) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sf.format(date);
		}
}
