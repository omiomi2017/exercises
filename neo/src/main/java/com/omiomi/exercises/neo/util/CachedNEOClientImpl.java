package com.omiomi.exercises.neo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;
import com.omiomi.exercises.neo.repository.NearEarthObjectDateListingRepository;
import com.omiomi.exercises.neo.repository.NearEarthObjectRepository;

/**
 * Facade that implements a caching layer for the Downloading NEO Client
 * 
 * @author omi
 */
@Component
public class CachedNEOClientImpl implements NEOClient{
	
	@Autowired private NEOClientDownloadingImpl downloader; 
	@Autowired private NearEarthObjectDateListingRepository dateListingRepo;
	@Autowired private NearEarthObjectRepository neoRepo;
	@Autowired private NEOClientUtils neoUtils;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public NearEarthObjectContainer getNearEarthObjectContainerForDateRange(String startDate, String endDate) {
		
		List<String> inclusiveDateList = DateCheck.genDateRange(startDate, endDate);
		/*
		 * Store dates for which we have a cache, we'll need it
		 * to make a set difference with inclussiveDateList
		 * to generate items to download
		 */
		List<String> datesCached = new ArrayList<>(); 
		NearEarthObjectContainer cachedNeoContainer = new NearEarthObjectContainer();	
		cachedNeoContainer.setDateNeoMap(new ConcurrentHashMap<>());
		List<NearEarthObjectContainer> neoContainers = new ArrayList<>();
		//Check if there are date ranges that are cached
		inclusiveDateList.forEach(date->{
			Optional<List<NearEarthObject>> neosForDate = dateListingRepo.getNearEarthObjectForDate(date);
			if(neosForDate.isPresent()) {
				//add neo objects for the given date
				neosForDate.get().forEach(neo->{cachedNeoContainer.addNearEarthObjectForDate(date, neo);}); 
				//add date to list of cached dates
				datesCached.add(date);
			}
		});
		
		List<String> datesToDownload = new ArrayList<>(inclusiveDateList);
		datesToDownload.removeAll(datesCached);
		List<Interval> downloadIntervals = DateCheck.getIntervalsFromDateList(datesToDownload);
			
		//download non-cached from downloader
		downloadIntervals.forEach(interval->{
			NearEarthObjectContainer downloadedNeoContainer = 
					downloader.getNearEarthObjectContainerForDateRange(
							interval.getStart(), interval.getEnd());
			logger.info("Downloading neoContainer... got {} dates",downloadedNeoContainer.getDateNeoMap().keySet());
			//update cache
			dateListingRepo.addAll(downloadedNeoContainer);
			neoContainers.add(downloadedNeoContainer);
		});
		neoContainers.add(cachedNeoContainer);
		
		return neoUtils.mergeNEOList(neoContainers);
	}

	@Override
	public NearEarthObject getDetailedNearEarthObjectByID(int neoID) {
		Optional<NearEarthObject> opt= neoRepo.getNEOById(neoID);
		if(opt.isPresent()) {
			
			return opt.get(); //return from cache
		}
		else {
			NearEarthObject downloadedNEO = downloader.getDetailedNearEarthObjectByID(neoID);
			//add to cache
			neoRepo.addNEO(downloadedNEO);
			return downloadedNEO;
		}
	}

	
}
