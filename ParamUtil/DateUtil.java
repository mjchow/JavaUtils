import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类
 * @ClassName: DateUtil
 * @author zero
 * @date 2015-8-9 下午7:49:46
 */
public class DateUtil {
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public final static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	/**
	 * yyyy-MM-dd
	 */
	public final static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public final static SimpleDateFormat yyyyMMddHHmmss1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * yyyy/MM/dd HH:mm
	 */
	public final static SimpleDateFormat yyyyMMddHHmm1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	/**
	 * yyyy/MM/dd
	 */
	public final static SimpleDateFormat yyyyMMdd1 = new SimpleDateFormat("yyyy/MM/dd");
	
	/**
     * 将字符串形式日期转换成日期(java.util.Date)<br/>
     * @param dateStr 时间字符串
     * @param 当前时间字符串的格式。
     * @return Date 日期 ,转换异常时返回null。
     */
    public static Date parseDate(String dateStr,SimpleDateFormat dataFormat){
        Date d = null;;
		try {
			d = dataFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return d;
    }
    
    /**
     * 将data转String类型的显示模式
     * @param date
     * @param dataFormat
     * @return
     */
    public static String formatDate(Date date, SimpleDateFormat dataFormat) {
    	return dataFormat.format(date);
    }
    
    /**
     * 将data转String类型的显示模式
     * @param time   时间戳  
     */
    public static String formatDate(Long time, SimpleDateFormat dataFormat) {
        Date date = new Date(time);
        return dataFormat.format(date);
    }
}
