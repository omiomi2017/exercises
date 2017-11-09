package com.omiomi.exercises.neo.util;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.omiomi.exercises.neo.config.AppConfig;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;
import com.omiomi.exercises.neo.service.APIKeyService;

/**
 * Retrieves list of near Earth objects using NASA APIs.
 * @author omi
 *
 */
@Component
@EnableAutoConfiguration
public class NEOClientDownloadingImpl implements NEOClient{
	
	@Autowired AppConfig config;
	@Autowired private RestTemplate restTemplate;
	@Autowired private NEOClientURLUtils urlHelper;
	@Autowired private NEOClientUtils neoClientUtils;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public NEOClientDownloadingImpl() {}
	
	APIKeyService apiKeyService;
	/**
	 * set API Key service
	 * Need to get API keys to make queries if want to request many items
	 * @param apiKeyService
	 */
	@Autowired
	public void setApiKeyService(APIKeyService apiKeyService) {
		this.apiKeyService=apiKeyService;
	}

	@Override
	public NearEarthObjectContainer getNearEarthObjectContainerForDateRange(String startDate, String endDate) {
		return neoClientUtils.mergeNEOList(
				getNearEarthObjectContainerListForDateRange(
						startDate, endDate));
	}
	
	/**
	 * Start:End date will be chunked into consumable 7-day periods (API limit)
	 * Each download operation will be stored in a NEOContainer;
	 * List of such results of download operations will be returned as List of NEOCOntainers
	 * @param startDate start date in ISO format
	 * @param endDate end date in ISO format
	 * @return List of download operations 
	 * @throws IllegalArgumentException
	 */
	public List<NearEarthObjectContainer> getNearEarthObjectContainerListForDateRange(String startDate, String endDate) 
			throws IllegalArgumentException{
		logger.info("getNearEarthObjectContainerListForDateRange({},{})",startDate,endDate);
		List<String> urlList = urlHelper.chunkURLDateRange(startDate, endDate); //get list of URLs
		logger.info("getNearEarthObjectContainerListForDateRange will download {} ", urlList);
		List<NearEarthObjectContainer> neoContainers = 
				urlList.stream()
				.map(item->restTemplate.getForObject(item, NearEarthObjectContainer.class)) //all or nothing downloading!
				.collect(Collectors.toList());
		return neoContainers;
	}
	
	
	//Download detailed information about a near earth object
	public NearEarthObject getDetailedNearEarthObjectByID(int neoID) {
		String detailedURL = urlHelper.getDetailedNeoInfoURL(neoID);
		logger.info("detailed({}): {}",neoID,detailedURL);
		return restTemplate.getForObject(detailedURL, NearEarthObject.class);
	}


}
