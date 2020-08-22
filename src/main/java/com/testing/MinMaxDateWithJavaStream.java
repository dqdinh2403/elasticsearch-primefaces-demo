package com.testing;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MinMaxDateWithJavaStream {
	
	public static boolean isSaturday(LocalDateTime givenDate) {
		return givenDate.getDayOfWeek().equals(DayOfWeek.SATURDAY);
	}

	public static void main(String[] args) {
		
		List<LocalDateTime> workedHourRecords = Arrays.asList(
				LocalDateTime.of(2020, 1, 6, 1, 0),
				LocalDateTime.of(2020, 1, 7, 1, 0),
				LocalDateTime.of(2020, 1, 8, 1, 0),
				LocalDateTime.of(2020, 1, 9, 1, 0),
				LocalDateTime.of(2020, 1, 10, 1, 0),
				LocalDateTime.of(2020, 1, 11, 1, 0),
			
				LocalDateTime.of(2020, 1, 13, 1, 0),
				LocalDateTime.of(2020, 1, 14, 1, 0),
				LocalDateTime.of(2020, 1, 15, 1, 0),
				LocalDateTime.of(2020, 1, 16, 1, 0),
				LocalDateTime.of(2020, 1, 17, 1, 0)
				);
		
		LocalDateTime recordFromDate = workedHourRecords.stream().min(LocalDateTime::compareTo).get();
		LocalDateTime recordToDate = workedHourRecords.stream().max(LocalDateTime::compareTo).get();
		
		while (!isSaturday(recordToDate)) {
			recordToDate = recordToDate.plusDays(1);
		}
		
		System.out.println(recordFromDate);
		System.out.println(recordToDate);
		
		
//		LocalDateTime recordFromDate = workedHourRecords.stream().map(WorkedHour::getDate).min(LocalDateTime::compareTo).get();
//		LocalDateTime recordToDate = workedHourRecords.stream().map(WorkedHour::getDate).max(LocalDateTime::compareTo).get();
	}

}
