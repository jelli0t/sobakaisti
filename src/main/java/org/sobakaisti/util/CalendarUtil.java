package org.sobakaisti.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.util.calendar.CalendarUtils;

public class CalendarUtil {
	private static final Logger logger = LoggerFactory.getLogger(CalendarUtil.class);
	public static final String INPUT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final SimpleDateFormat INPUT_DATETIME_FORMAT = new SimpleDateFormat(INPUT_DATETIME_PATTERN);
	
	private static final String BASIC_DATE_FORMAT = "dd.MM.yyyy";
	public static final SimpleDateFormat basicDateFormatter = new SimpleDateFormat(BASIC_DATE_FORMAT);
	
	private static CalendarUtil instance = null;
	private Calendar calendar = null;
	
	public static List<String> months = new ArrayList<String>(13);
	public static final int minYear = 2010;
	public static final int maxYear = 2090;
	public int maxDate;
	public int year;
	public int date;
	public int month;
	public int hour;
	public int minute;
	
	
	public static CalendarUtil getInstance() {
		 if(instance == null){
			 instance = new CalendarUtil();
	     }
		 return instance; 
	}
	
	public static CalendarUtil getInstance(Calendar then) {
		 if(instance == null){
			 instance = new CalendarUtil(then);
	     }
		 return instance; 
	}
	
	/**
	 * Defaultni konstruktor setuje polja po trenutnom vremenu
	 * */
	public CalendarUtil() {
		calendar = Calendar.getInstance();
		this.month = calendar.get(Calendar.MONTH);
		this.date = calendar.get(Calendar.DATE);
		this.year = calendar.get(Calendar.YEAR);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.maxDate = calendar.getActualMaximum(Calendar.DATE);
	}
	
	/**
	 * Konstruktor koji setuje polja na osnovu prosledjenog datuma
	 * @param then
	 * */
	public CalendarUtil(Calendar then) {
		this.calendar = then;
		this.month = then.get(Calendar.MONTH);
		this.date = then.get(Calendar.DATE);
		this.year = then.get(Calendar.YEAR);
		this.hour = then.get(Calendar.HOUR_OF_DAY);
		this.minute = then.get(Calendar.MINUTE);
		this.maxDate = then.getActualMaximum(Calendar.DATE);
	}	
	
	
	/**
	 * Konvertuje Date objekat u Calendar
	 * */
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(date);	
		return calendar;		
	}
	
	/**
	 * vraca max broj dana u zadatom mesecu
	 * @param month
	 * */
	public static int getMaxDatePerMonth(int month) {
		Calendar calendar = Calendar.getInstance();	
		calendar.set(Calendar.MONTH, month);
		final String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		final int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		logger.info("Za mesec "+monthName+" nalazim da ima "+maxDays+" dana.");
		return maxDays;
	}
	
	
	public Map<String, Integer> getCalendarFieldNamesMap(int filed) {
		return calendar.getDisplayNames(filed, Calendar.SHORT, Locale.getDefault());
	}
	
	/**
	 * Parsuje datum iz prosledjenog Stringa prema sablonu prosledjenom kao SimpleDateFormat.
	 * @param dateString	datum u textualnom formatu
	 * @param format		sablon za parsiranje datuma
	 * */
	public Calendar parseCalendarFromString(String dateString, SimpleDateFormat format) {
		if(dateString != null) {
			logger.info("Parsiram datum iz Stringa: '"+dateString+"', po sablonu: "+format.toPattern());
			try {
				calendar.setTime(format.parse(dateString));
				logger.info("Postavljen datum: "+calendar.getTime()+", parsiran iz Stringa.");
			} catch (ParseException e) {
				logger.warn("Greska prilikom parsiranja datuma iz String-a.");
			}
		}
		return calendar;
	}
}
