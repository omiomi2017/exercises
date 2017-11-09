package com.omiomi.exercises.neo.repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;

/**
 * Repository which contains an overview of NearEarthObjects,
 * which were found on a given date 
 * (similar to an index of /feed? NASA NEO API for each date)
 * 
 * @author omi
 *
 */
public interface NearEarthObjectDateListingRepository {
	
	/**
	 * Get a list of NearEarthObject for a given date
	 * @param date ISO8601 YYYY-MM-DD representation of a date
	 * @return an option with List of NearEarthObject.
	 */
	public Optional<List<NearEarthObject>> getNearEarthObjectForDate(String date);
	
	
	/**
	 * Set(overwrite) the list of NearEarthObject for a given date
	 * @param date  ISO8601 YYYY-MM-DD representation of a date
	 * @param neoList List of NearEarthObject to set for the given date
	 * @return true if the neoList was set for the given date.
	 */
	public boolean setNearObjectListForDate(String date, Collection<NearEarthObject> neoList);
	
	/**
	 * Check if there is data for the given date
	 * @param date ISO8601 YYYY-MM-DD representation of a date
	 * @return true if there is data contains for the given date.
	 */
	public boolean containsDataForDate(String date);
	
	/**
	 * Add new NearEarthObject for the given date
	 * @param date ISO8601 YYYY-MM-DD representation of a date
	 * @param newNeo new NearEarthObject object to add
	 * @return if the object was added return true
	 */
	public boolean addNearEarthObjectForDate(String date, NearEarthObject newNeo);
	
	
	/**
	 * Returns whether NearEarthObject is contained for a given date
	 * @param date ISO8601 YYYY-MM-DD representation of a date
	 * @param newNeo add this NearEarthObject
	 * @return true if we have the specified neo for the given date
	 */
	
	public boolean containsNearEarthObjectForDate(String date, NearEarthObject newNeo);
	
	/**
	 * Look up a NearEarthObject for the given date and ID
	 * @param date date ISO8601 YYYY-MM-DD representation of a date
	 * @param neoID NEO ID
	 * @return option for NearEarthObject for the given date and neo ID
	 */
	public Optional<NearEarthObject> getNearEarthObjectByDateAndID(String date, Integer neoID);
	
	
	public boolean addAll(String date, Collection<NearEarthObject> neoValues);
	
	/**
	 * Add all entries from given container into the repository
	 * @param neoCont NEOContainer
	 * @return true if all entries were successfully added.
	 */
	public boolean addAll(NearEarthObjectContainer neoCont);
	
	/**
	 * Return total number of objects stored in the repository
	 * @return
	 */
	public long size();
	
	/**
	 * Clear the repository
	 */
	public void clear();
}
