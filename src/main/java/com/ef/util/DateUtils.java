package com.ef.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date manipulation utility class.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class DateUtils {

	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
	static Calendar calendar = Calendar.getInstance();
	static Date date;
	
	public static Date getStartDateFromString(String dateStr) throws ParseException {
		date = formatter.parse(dateStr);

		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
	
	public static Date getEndDateFromString(String dateStr, int hours) throws ParseException {
		date = formatter.parse(dateStr);

		calendar.setTime(date);

		calendar.add(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}
}
