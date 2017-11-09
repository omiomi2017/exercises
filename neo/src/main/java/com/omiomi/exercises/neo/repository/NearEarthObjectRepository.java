package com.omiomi.exercises.neo.repository;

import java.util.Optional;

import com.omiomi.exercises.neo.domain.NearEarthObject;

/**
 * Interface for NearEarthObject Repository
 * Contains detailed representation of NearEarthObjects 
 * (list of close approaches and orbital data)
 * This data is cached from the detailed feed 
 * @author omi
 *
 */
public interface NearEarthObjectRepository {
	
	/**
	 * Get NearEarthOBject by ID
	 * @param id ID of the near earth object
	 * @return NearEarthObject for the ID 
	 */
	public Optional<NearEarthObject> getNEOById(Integer id);
	
	/**
	 * Check if NearEarthObject is cached here
	 * @param id NearEarthObject id
	 * @return true if the repository contains it
	 */
	public boolean containsNEOWithID(Integer id);
	
	/**
	 * Check if a given NearEearthObject is contained in this repository
	 * @param neo NearEarthOBject
	 * @return true if the given neo is contained in the repository
	 */
	public boolean containsNEO(NearEarthObject neo);
	
	/**
	 * Insert a NearEarthObject
	 * (Used for storing detailed NearEarthObjects)
	 * @param neo NearEarthObject to add
	 * @return true if the value was added
	 */
	public boolean addNEO(NearEarthObject neo);
	
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
