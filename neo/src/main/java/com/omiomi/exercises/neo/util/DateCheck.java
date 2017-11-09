package com.omiomi.exercises.neo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



/**
 * Convenience wrapper for various date checks
 * @author omi
 *
 */
public class DateCheck {
	public static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
	/**
	 * Check if the date is in ISO8601 format
	 * @param date date to check
	 * @return true if the date is in valid format and is valid
	 */
	public static boolean isISODate(String date) {
		try{
			LocalDate.parse(date,ISO_DATE);
			return true;
		}catch(DateTimeParseException dtpe) {
			return false;
		}
	}
	
	/**
	 * Generate an inclusive list of ISO dates from startDate to endDate
	 * @param startDate start date in ISO8601 representation
	 * @param endDate end date in ISO8601 representation
	 * @return List containing all values
	 */
	public static List<String> genDateRange(String startDate, String endDate){
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		List<String> datesList = new ArrayList<>();
		LocalDate tmp = start;
		while(tmp.isBefore(end)) {
			datesList.add(tmp.format(ISO_DATE));
			tmp=tmp.plusDays(1);
		}
		datesList.add(tmp.format(ISO_DATE));
		return datesList;
	}

	
	/**
	 * Convert a list of days to a List of Intervals 
	 * @param dateList input list of Dates
	 * @return list of intervals 
	 */
	public static List<Interval> getIntervalsFromDateList(List<String> dateList){
		List<Interval> ret = new ArrayList<>();
		if(dateList.isEmpty()) {}
		else if(dateList.size()==1) { //handle the case with interval starting:ending on same date
			ret.add(new Interval(dateList.get(0), dateList.get(0)));		
		}else { //handle case of two or more dates
			List<LocalDate> sortedDateList = dateList.stream().sorted()
					.map(date -> LocalDate.parse(date))
					.collect(Collectors.toList());
			
			LocalDate start = sortedDateList.get(0); //we're guaranteed to have at least 2 element here
			LocalDate end = sortedDateList.get(sortedDateList.size()-1);
			LocalDate tmp=start;
			
			for(int i=1;i<sortedDateList.size();i++) {
				LocalDate curr = sortedDateList.get(i);
				if( tmp.plusDays(1).equals(curr) ) tmp=curr; else {//still sequential -shift pointer up
					ret.add(new Interval(start.toString(),tmp.toString()));
					start=tmp=curr;
				}
			}
			ret.add(new Interval(start.toString(),end.toString()));
		}
		
		return ret;
	}
	
}
