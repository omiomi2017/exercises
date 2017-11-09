package com.omiomi.exercises.neo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omiomi.exercises.neo.domain.FlatNEO;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;
import com.omiomi.exercises.neo.util.CachedNEOClientImpl;
import com.omiomi.exercises.neo.util.DateCheck;
import com.omiomi.exercises.neo.util.NEOClientUtils;

/**
 * Service class for NearEarthObjects. calls utility methods
 * @author omi
 *
 */
@Service
public class NEOService {
	
	@Autowired private CachedNEOClientImpl client;
	@Autowired private NEOClientUtils neoClientUtils;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private void throwExpectionForNonISODates(String dateFrom, String dateTo) {
		if(!DateCheck.isISODate(dateFrom)) throw new IllegalArgumentException("dateFrom is not in ISO8601 format");
		if(!DateCheck.isISODate(dateTo)) throw new IllegalArgumentException("dateFrom is not in ISO8601 format");
	}
	
	public List<FlatNEO> getClosestNearEarthObjects(String dateFrom, String dateTo) {
		throwExpectionForNonISODates(dateFrom, dateTo);
			
		logger.info("Entered getClosestNearEarthObjects({},{})",dateFrom,dateTo);
		NearEarthObjectContainer neoc = client
				.getNearEarthObjectContainerForDateRange(dateFrom, dateTo);
		
		List<FlatNEO> sortedAscListByDistance = 
				neoClientUtils.sortNEOByDistanceToEarth(neoc); //closest is ascending sort
		return sortedAscListByDistance;
	}
	
	public List<FlatNEO> getBiggestNearEarthObjects(String dateFrom, String dateTo) {
		throwExpectionForNonISODates(dateFrom, dateTo);
		logger.info("Entered getBiggestNearEarthObjects({},{})",dateFrom,dateTo);
		NearEarthObjectContainer neoc = 
				client.getNearEarthObjectContainerForDateRange(dateFrom, dateTo);
		List<FlatNEO> sortedDescListBySize = neoClientUtils.sortNEOBySize(neoc); //size sort is Desc
		return sortedDescListBySize;
	}

	
	/**
	 * Return full details about a Near Earth Object
	 * @param neoID integer ID of NEO
	 * @return full NearEarthObject
	 */
	public NearEarthObject getDetailedNEO(int neoID) {
		logger.info("Entered getDetailedNEO({})", neoID);
		return client.getDetailedNearEarthObjectByID(neoID);
	}
}
