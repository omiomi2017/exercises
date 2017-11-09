package com.omiomi.exercises.neo.util;

import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;

/**
 * Define NEOClient abstract class
 * Downloading and Cached NEOClient will share the same interface.
 * 
 * @author omi
 *
 */
public interface NEOClient {
	/**
	 * Get a list of NEO Containers for a given date range
	 * Represents List of Date->{NearEarthObject 1..n}
	 * @param startDate start date in ISO8601 YYYY-MM-DD representation
	 * @param endDate end date in ISO8601 YYYY-MM-DD representation
	 * @return NearEarthObjectContainer
	 */
	public NearEarthObjectContainer getNearEarthObjectContainerForDateRange(String startDate, String endDate);
	
	/**
	 * Gets a detailed representation of a NearEarthObject by its integer ID.
	 * The difference between this method and extracting one element from the result of
	 * from{@link #getNearEarthObjectContainerForDateRange(String,String)} is 
	 * there will be a. several instances of close_approach_data and b.orbital_data will be populated
	 * @param neoID ID of the NearEarthObject
	 * @return
	 */
	public NearEarthObject getDetailedNearEarthObjectByID(int neoID);
}
