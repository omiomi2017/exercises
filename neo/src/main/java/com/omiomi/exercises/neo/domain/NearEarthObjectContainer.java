package com.omiomi.exercises.neo.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stores the list of near earth objects.
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class NearEarthObjectContainer{
	
	/**
	 * Default constructor
	 */
	public NearEarthObjectContainer() {}
	
	@JsonProperty("element_count")
	private int elementCount;
	
	@JsonProperty("near_earth_objects")
	private Map<String,Set<NearEarthObject>> dateNeoMap;
	
	public int getElementCount() {
		return elementCount;
	}
	
	public void setElementCount(int elementCount) {
		this.elementCount = elementCount;
	}
	public Map<String, Set<NearEarthObject>> getDateNeoMap() {
		
		return dateNeoMap;
	}
	public void setDateNeoMap(Map<String, Set<NearEarthObject>> dateNeoMap) {
		this.dateNeoMap = dateNeoMap;
	}
	
	/**
	 * Add a NearEarthOBject for a given date to existing map
	 * @param date date
	 * @param neo Near Earth Object 
	 */
	public void addNearEarthObjectForDate(String date, NearEarthObject neo) {
		if(dateNeoMap.containsKey(date)) {
			dateNeoMap.get(date).add(neo);
		}else {
			Set<NearEarthObject> listContainer = new LinkedHashSet<NearEarthObject>();
			listContainer.add(neo);
			dateNeoMap.put(date, listContainer);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NearEarthObjectContainer [elementCount=" + elementCount + ", dateNeoMap=" + dateNeoMap + "]";
	}
	
}
