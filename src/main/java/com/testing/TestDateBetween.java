package com.testing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TestDateBetween {

	public static void main(String[] args) {
		
		List<Date> result = new ArrayList<>();

		List<Date> holidays = new ArrayList<>();
		holidays.add(new Date(119, 10, 13));
		holidays.add(new Date(119, 10, 18));
		
		Date start = new Date(119, 10, 12, 13, 30, 00);
		Date end = new Date(119, 10, 20, 12, 00, 00);

		// convert date to calendar
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);

		if (!isSameDay(startCalendar, endCalendar)) {
			result = getAllDaysBetweenTwoDays(startCalendar, endCalendar);
			result.add(start);
			result.add(end);
			result = ignoreWeekend(result);
			result = ignoreHoliday(result, holidays);
			
		} else {
			// get date of start date, time of start date + time of end date => convert to DayOff
			// add(DayOff);
		}
		
		System.out.println(result);
	}

	private static List<Date> ignoreHoliday(List<Date> days, List<Date> holidays) {
		List<Date> result = days.stream().filter(day -> !holidays.contains(day)).collect(Collectors.toList());
//		isSameDay();
		return result;
	}

	private static boolean isWeekend(Calendar day) {
		boolean isWeekend = false;
		if(day.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || day.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			isWeekend = true;
		}
		return isWeekend;
	}
	
	private static List<Date> ignoreWeekend(List<Date> days) {
		List<Date> result = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		
		for (Date day : days) {
			calendar.setTime(day);
			if(!isWeekend(calendar)) {
				result.add(day);
			}
		}
		
		return result;
	}
	
	private static List<Date> getAllDaysBetweenTwoDays(Calendar startCalendar, Calendar endCalendar) {
		List<Date> result = new ArrayList<>();
		
		// plus 1 day to ignore start date
		startCalendar.add(Calendar.DAY_OF_MONTH, 1);
		
		// reset time
		startCalendar.set(Calendar.HOUR_OF_DAY, 8);
		startCalendar.set(Calendar.MINUTE, 0);
		
		while (startCalendar.get(Calendar.DAY_OF_MONTH) < endCalendar.get(Calendar.DAY_OF_MONTH)) {
			Date eachDate = startCalendar.getTime();
			result.add(eachDate);
			startCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return result;
	}

	private static boolean isSameDay(Calendar start, Calendar end) {
		boolean isEqual = false;
		
		// check without time
		if (start.get(Calendar.DAY_OF_MONTH) == end.get(Calendar.DAY_OF_MONTH) && start.get(Calendar.MONTH) == end.get(Calendar.MONTH)
				&& start.get(Calendar.YEAR) == end.get(Calendar.YEAR)) {
			isEqual = true;
		}
		
		return isEqual;
	}
}
