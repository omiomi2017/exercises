package com.omiomi.exercises.neo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.omiomi.exercises.neo.util.DateCheck;
import com.omiomi.exercises.neo.util.Interval;

public class DateCheckTest {
	//Check for valid date
	@Test
	public void checkValidDate() {
		assertTrue(DateCheck.isISODate("2017-01-01"));
	}
	//Check for invalid date
	@Test
	public void checkInvalidDate_badFormat() {
		assertFalse(DateCheck.isISODate("2017-01"));
		assertFalse(DateCheck.isISODate("2017-1-1"));
		assertFalse(DateCheck.isISODate("2017"));
	}
	//Check for empty
	@Test
	public void failOnEmptyDate() {
		assertFalse(DateCheck.isISODate(""));
		assertFalse(DateCheck.isISODate(" "));
		assertFalse(DateCheck.isISODate(" - - -"));
	}
	
	//Valid format but nonsensical values should fail
	@Test
	public void failGivenValidFormatNonsensicalInput() {
		assertFalse(DateCheck.isISODate("2017-35-99"));
	}
	
	//check date range generation
	@Test
	public void checkGeneratingValidRange() {
		String startDate = "2017-01-30";
		String endDate = "2017-02-03";
		List<String> result = DateCheck.genDateRange(startDate, endDate);
		List<String> expected = Arrays.asList(startDate,"2017-01-31","2017-02-01","2017-02-02", endDate);
		assertEquals(expected,result);
	}
	
	//check getIntervalsFromDateList
	@Test
	public void checkgetIntervalsFromDateList() {
		List<String> emptyDate = Arrays.asList();
		List<Interval> emptyDateExpected = Arrays.asList();
		assertEquals(emptyDateExpected,DateCheck.getIntervalsFromDateList(emptyDate));
		
		List<String> singleDate = Arrays.asList("2017-01-01");
		List<Interval> singleDateExpected = Arrays.asList(new Interval("2017-01-01","2017-01-01"));
		assertEquals(singleDateExpected,DateCheck.getIntervalsFromDateList(singleDate));
		
		List<String> range1 = Arrays.asList("2017-01-30","2017-01-31","2017-02-01","2017-02-02", "2017-02-03");
		List<Interval> range1Expected = Arrays.asList(new Interval("2017-01-30","2017-02-03"));
		assertEquals(range1Expected,DateCheck.getIntervalsFromDateList(range1));
		
		//test a single followed by a range case
		List<String> range2 = Arrays.asList("2017-01-30" ,"2017-02-01","2017-02-02", "2017-02-03");
		List<Interval> range2Expected = Arrays.asList(new Interval("2017-01-30","2017-01-30"),
				new Interval("2017-02-01","2017-02-03"));
		assertEquals(range2Expected,DateCheck.getIntervalsFromDateList(range2));
		
		//test a case of two ranges
		List<String> range3 = Arrays.asList("2017-01-30","2017-01-31", "2017-02-02", "2017-02-03");
		List<Interval> range3Expected = Arrays.asList(new Interval("2017-01-30","2017-01-31"),
				new Interval("2017-02-02", "2017-02-03"));
		assertEquals(range3Expected,DateCheck.getIntervalsFromDateList(range3));
		
		//test three groups of ranges
		List<String> range4 = Arrays.asList("2017-01-30","2017-01-31", 
				"2017-02-02", "2017-02-03", "2017-02-04",
				"2019-02-02");
		List<Interval> range4Expected = Arrays.asList(new Interval("2017-01-30","2017-01-31"),
				new Interval("2017-02-02", "2017-02-04"),
				new Interval("2019-02-02","2019-02-02"));
		assertEquals(range4Expected,DateCheck.getIntervalsFromDateList(range4));
	}
}
