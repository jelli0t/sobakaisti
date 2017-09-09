package org.sobakaisti.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sun.util.calendar.CalendarUtils;

public class CalendarUtil {
	
	public static List<String> months = new ArrayList<String>(13);
	public static final int minYear = 2010;
	public static final int maxYear = 2090;
	public int maxDate;
	public int year;
	public int date;
	public int month;
	public int hour;
	public int minute;
	
	/**
	 * Defaultni konstruktor setuje polja po trenutnom vremenu
	 * */
	public CalendarUtil() {
		Calendar now = Calendar.getInstance();
		this.month = now.get(Calendar.MONTH);
		this.date = now.get(Calendar.DATE);
		this.year = now.get(Calendar.YEAR);
		this.hour = now.get(Calendar.HOUR_OF_DAY);
		this.minute = now.get(Calendar.MINUTE);
		this.maxDate = now.getActualMaximum(Calendar.DATE);
	}
	
	/**
	 * Konstruktor koji setuje polja na osnovu prosledjenog datuma
	 * @param then
	 * */
	public CalendarUtil(Calendar then) {
		this.month = then.get(Calendar.MONTH);
		this.date = then.get(Calendar.DATE);
		this.year = then.get(Calendar.YEAR);
		this.hour = then.get(Calendar.HOUR_OF_DAY);
		this.minute = then.get(Calendar.MINUTE);
		this.maxDate = then.getActualMaximum(Calendar.DATE);
	}	
	
	static {
		months.add(0, "Jan");
		months.add(1, "Feb");
		months.add(2, "Mar");
		months.add(3, "Apr");
		months.add(4, "Maj");
		months.add(5, "Jun");
		months.add(6, "Jul");
		months.add(7, "Avg");
		months.add(8, "Sep");
		months.add(9, "Okt");
		months.add(10, "Nov");
		months.add(11, "Dec");
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
		System.out.println("new date: "+calendar.getTime());
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
}
