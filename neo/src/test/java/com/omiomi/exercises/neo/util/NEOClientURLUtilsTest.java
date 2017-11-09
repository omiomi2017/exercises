package com.omiomi.exercises.neo.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.config.AppConfig;
import com.omiomi.exercises.neo.util.NEOClientURLUtils;

@SpringBootTest
public class NEOClientURLUtilsTest extends AbstractTest{
	@Autowired AppConfig config;
	@Autowired private NEOClientURLUtils urlHelper;
	
	/**
	 * Generate feed URL for API request between (start,end)date
	 * @param startDate YYYY-MM-DD start date representation
	 * @param endDate YYYY-MM-DD end date representation
	 * @return Formatted URL for the request
	 */
	private String genFeedURL(String startDate, String endDate) {
		return String.format("%sfeed?start_date=%s&end_date=%s&detailed=false&api_key=%s", 
				config.getApiBaseURL(), startDate, endDate, config.getAPIKey());
	}
	
	private String genDetailedURL(Integer neoID) {
		return String.format("https://api.nasa.gov/neo/rest/v1/neo/%d?api_key=%s", neoID, config.getAPIKey());
	}
	
	/**
	 * Ensure feed url is correct for a date range
	 */
	@Test
	public void testCorrectURL_forDateRange() {
		String startDate ="2017-10-01";
		String endDate="2017-10-02";
		String result=urlHelper.getURLByDateRange(startDate, endDate);
		String expected = genFeedURL(startDate, endDate);
		assertEquals(result,expected);
	}
	

	/**
	 * Test detailed NEO link generation
	 */
	@Test
	public void checkDetailedURLGeneration() {
		int neoID=1234;
		String expected = genDetailedURL(neoID);
		String result = urlHelper.getDetailedNeoInfoURL(neoID);
		assertEquals(expected,result);
	}

	
	/**
	 * Ensure feed URL is correct for a single date
	 */
	@Test
	public void testCorrectURL_forSingleDate() {
		String date ="2017-10-01";
		String result=urlHelper.getURLByDateRange(date, date);
		String expected = genFeedURL(date,date);
		assertEquals(result, expected);
	}
}
