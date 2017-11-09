package com.omiomi.exercises.neo.util;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omiomi.exercises.neo.config.AppConfig;
import com.omiomi.exercises.neo.service.APIKeyService;

@Component
public class NEOClientURLUtils {

	private static final String START_DATE = "start_date";
	private static final String END_DATE = "end_date";
	private static final String API_KEY = "api_key";
	private static final String DETAILED = "detailed";
	private static final String FEED = "feed";
	private static final String NEO = "neo";

	@Autowired
	private AppConfig config; 
	
	@Autowired
	private APIKeyService apiKeyService;
	

	/**
	 * Build API request string
	 * 
	 * @param startDate
	 *            ISO_LOCAL_DATE formatted start date
	 * @param endDate
	 *            ISO_LOCAL_DATE formatted end date
	 * @return URL for the request for the given date range
	 */
	public String getURLByDateRange(String startDate, String endDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(config.getApiBaseURL()).append(FEED).append("?").append(START_DATE).append("=").append(startDate).append("&")
				.append(END_DATE).append("=").append(endDate).append("&")
				.append(DETAILED).append("=").append(Boolean.FALSE.toString()).append("&")
				.append(API_KEY).append("=").append(apiKeyService.getApiKey());
		return sb.toString();
	}

	/**
	 * Get a URL for a single date
	 * 
	 * @param date
	 *            ISO_LOCAL_DATE formatted date
	 * @return URL for the request for the given date
	 */
	public String getSingleDateURL(String date) {
		return getURLByDateRange(date, date);
	}

	/**
	 * Generate a URL for reference to an object
	 * Client is responsible for validating parameters
	 * @return URL to JSON object describing a NearEarthObject properties
	 */
	public String getDetailedNeoInfoURL(int neoReferenceId) {
		StringBuilder sb = new StringBuilder();
		sb.append(config.getApiBaseURL()).append(NEO).append("/")
		.append(neoReferenceId).append("?")
			.append(API_KEY).append("=").append(apiKeyService.getApiKey());
		return sb.toString();
	}

	/**
	 * NASA NEO API allows only up to 7 days of data to be retrieved This function
	 * partitions specified date range into 7 day chunks and produces a list of URIs
	 * 
	 * @param dateStart
	 *            start date
	 * @param dateEnd
	 *            end date
	 * @throws IllegalArgumentException
	 *             if dateEnd is before dateStart
	 * @return List of URIs for the given date range.
	 */
	public List<String> chunkURLDateRange(String dateStart, String dateEnd) throws IllegalArgumentException {
		LocalDate start,end;
		try {
			start = LocalDate.parse(dateStart, ISO_LOCAL_DATE);
			end = LocalDate.parse(dateEnd, ISO_LOCAL_DATE);
		}catch(DateTimeParseException dtpe) {
			throw new IllegalArgumentException("Error parsing date\n", dtpe.getCause());
		}
		if (end.isBefore(start))
			throw new IllegalArgumentException("dateEnd cannot be earlier than dateStart");
		
		if(dateStart.equals(dateEnd)) return Arrays.asList(this.getSingleDateURL(dateStart));
		List<String> uriList = new ArrayList<String>();
	
		/* Partition the date range into max number of days as allowed by API
		 * Loop through the date range, comparing end date.
		 * Terminate when curr is no longer before end date
		 * */
		
		LocalDate curr = LocalDate.from(start);
		while (curr.isBefore(end)) {
			if (DAYS.between(curr, end) > config.getApiMaxDaysPerRequest()) {
				LocalDate maxDaysLater = curr.plusDays(config.getApiMaxDaysPerRequest());
				uriList.add(getURLByDateRange(curr.format(ISO_LOCAL_DATE), 
						maxDaysLater.format(ISO_LOCAL_DATE)));
				curr = maxDaysLater; // advance current date by maxdays.
			}else { //add curr,end interval
				uriList.add(getURLByDateRange(curr.format(ISO_LOCAL_DATE), 
						end.format(ISO_LOCAL_DATE)));
				curr=end; //terminate
			}
		}
		return uriList;
	}

}