package com.omiomi.exercises.neo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.config.AppConfig;
import com.omiomi.exercises.neo.domain.FlatNEO;
import com.omiomi.exercises.neo.domain.NEOJsonGenerator;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;
import com.omiomi.exercises.neo.service.APIKeyService;
import com.omiomi.exercises.neo.util.NEOClientDownloadingImpl;
import com.omiomi.exercises.neo.util.NEOClientURLUtils;
import com.omiomi.exercises.neo.util.NEOClientUtils;

/**
 * Test methods in NEOClient utility class
 * @author omi
 *
 */
@SpringBootTest
public class NEOClientDownloadingImplTest extends AbstractTest {
	
	@Autowired AppConfig config;
	@Autowired private NEOClientDownloadingImpl client;
	@Autowired private NEOClientURLUtils urlHelper;
	@Autowired private NEOClientUtils neoClientUtils;
	
	@SuppressWarnings("unused") 
	@Autowired private APIKeyService apiKey;
	
	@Test
	public void configLoaded() {
		assertNotNull(config);
	}
	
	@Test
	public void clientLoaded() {
		assertNotNull(client);
	}
	
	//Don't download data for now - better than many @Ignores to remove
	private boolean runningTestsThatDownloadData=false;


	
	@Test
	public void chunkURLDateRangeList_forOneDayDateRange() {
		String startDate = "2017-10-01";
		String endDate = startDate;
		List<String> listOfUrls = urlHelper.chunkURLDateRange(startDate, endDate);
		assertTrue(listOfUrls.size()==1);
		assertTrue(listOfUrls.get(0).contains(startDate));
	}
	
	/**
	 * Test if date range is split correctly for the #days exceeding API limit(7 days)
	 */
	@Test
	public void testChunkedUrlList_whenDateRangeEceedsApiLimit() {
		String startDate ="2017-10-01";
		String endDate = "2017-10-18";
		
		String expected1 = startDate;
		String expected2 = "2017-10-08";
		String expected3 = "2017-10-15";
		String expected4 = endDate;
		
		List<String> listOfUrls = urlHelper.chunkURLDateRange(startDate, endDate);
		assertEquals(listOfUrls.size(),3);
		List<String> expectedURLs = new ArrayList<>();
		expectedURLs.add(genFeedURL(expected1,expected2));
		expectedURLs.add(genFeedURL(expected2,expected3));
		expectedURLs.add(genFeedURL(expected3,expected4));
		assertTrue(listOfUrls.containsAll(expectedURLs));
	}
	
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

	
	/**
	 * Download NEO objects for 2017-10-01..2017-10-02
	 * TODO: add download caching to not exceed API download limits (iirc 50 per hour for demo)
	 */
	@Test
	public void download3WeeksOfNEOContainers() {
		assumeTrue(runningTestsThatDownloadData);
		String startDate = "2017-10-01";
		String endDate = "2017-10-18";
		List<NearEarthObjectContainer> neoContainers = 
				client.getNearEarthObjectContainerListForDateRange(startDate, endDate);
		neoContainers.forEach(neoCont->logger.debug(neoCont.toString()));
		assertEquals(neoContainers.size(),3);
	}
	
	/**
	 * Download NEO objects for 2017-10-01..2017-10-02
	 * TODO: add download caching to not exceed API download limits (iirc 50 per hour for demo)
	 */
	@Test
	public void downloadNEOContainer2() {
		assumeTrue(runningTestsThatDownloadData);
		String startDate = "2017-10-01";
		String endDate = "2017-10-02";
		List<NearEarthObjectContainer> neoContainers = 
				client.getNearEarthObjectContainerListForDateRange(startDate, endDate);
		assertEquals(neoContainers.size(),1);
	}
	
	/**
	 * Test converting from NearEarthObject to FlatNEO object
	 */
	@Test
	public void testConvertingToflatNEO() {
		NearEarthObject neo=NEOJsonGenerator.getNEOSample();
		FlatNEO flatNeo = neoClientUtils.convertToFlatNEO(neo);
		assertEquals(flatNeo, NEOJsonGenerator.getFlatNEOSample());
	}
	
	/**
	 * Test sorting NearEarthObjects by Size
	 */
	@Test
	public void testSortingNEO_bySize() {
		assumeTrue(runningTestsThatDownloadData);
		String startDate = "2017-10-01";
		String endDate = "2017-10-02";
		List<NearEarthObjectContainer> neoContainers = 
				client.getNearEarthObjectContainerListForDateRange(startDate, endDate);
		NearEarthObjectContainer neoc = neoClientUtils.mergeNEOList(neoContainers);
		List<FlatNEO> sortedListBySize = neoClientUtils.sortNEOBySize(neoc);
		//dump to console
		//sortedListBySize.stream().forEach(item->logger.info(item.toString()));
		assertNotNull(sortedListBySize);
		assertEquals(sortedListBySize.size(),27);
		//check that the collection is indeed sorted.
		List<Float> onlySizes= sortedListBySize.stream()
				.map(item-> (item.getEstimatedDiameterMax()+item.getEstimatedDiameterMin())/2)
				.collect(Collectors.toList());
		List<Float> sortedCopy = new ArrayList<Float>(onlySizes);
		assertTrue(onlySizes.equals(sortedCopy));
	}
	
	
	@Test
	public void checkDetailedNearEarthObject() {
		assumeTrue(runningTestsThatDownloadData);
		int neoID=3650046;
		NearEarthObject detailedNEO = client.getDetailedNearEarthObjectByID(neoID);
		assertNotNull(detailedNEO);
		//test a few fields
		logger.info(detailedNEO.toString());
		assertEquals(detailedNEO.getNeoReferenceId(),neoID);
		assertTrue(detailedNEO.getAbsoluteMagnitudeH()== 23.3);
		assertEquals(detailedNEO.getCloseApproach().size(),3);
		assertEquals(detailedNEO.getOrbitalData().getOrbit_id(),17);
	}
		
}
