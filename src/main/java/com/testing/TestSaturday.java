package com.testing;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestSaturday {
	
	public static boolean isSaturday(LocalDateTime givenDate) {
		return givenDate.getDayOfWeek().equals(DayOfWeek.SATURDAY);
	}

	public static LocalDateTime plusToNextSaturday(LocalDateTime recordToDate) {
		LocalDateTime nextSaturday = recordToDate;
		while (recordToDate.getMonthValue() == nextSaturday.getMonthValue() && !isSaturday(nextSaturday)) {
			nextSaturday = nextSaturday.plusDays(1);
		}
		return nextSaturday;
	}
	
	public static void main(String[] args) {
		LocalDateTime date = LocalDateTime.of(LocalDate.of(2020, 01, 28), LocalTime.of(0, 0));
		System.out.println(date);
		date = plusToNextSaturday(date);
		System.out.println(date);
	}

}
