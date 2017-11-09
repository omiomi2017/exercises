package com.omiomi.exercises.neo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omiomi.exercises.neo.domain.CloseApproachData;
import com.omiomi.exercises.neo.domain.FlatNEO;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;

/**
 * Datastructure utils for NEOClient
 * @author omi
 *
 */
@Component
public class NEOClientUtils {


	@Autowired private NEOClientURLUtils urlHelper;
	
	/**
	 * Merge a list of NEO containers into one NEO Container
	 * @param neoContainers
	 * @return merged NEOContainer
	 */
	
	public NearEarthObjectContainer mergeNEOList(List<NearEarthObjectContainer> neoContainers) {
		NearEarthObjectContainer mergedContainer=new NearEarthObjectContainer();
		
		//Normally NearEarthContainer object's dateNeoMap is set via JSON parsing
		//So we'll have to initialize and set a new {String->NearEarthObject} Map
		mergedContainer.setDateNeoMap(new LinkedHashMap<String, Set<NearEarthObject>>());
		//iterate over all near earth object containers and add the date values to merged container.
		neoContainers.forEach(neoContainer -> {
				neoContainer.getDateNeoMap().forEach((neoContainerDate, neoList) ->{
					neoList.forEach(neo ->{
						mergedContainer.addNearEarthObjectForDate(neoContainerDate, neo);
					});
				});
			});
		return mergedContainer;
	}
	

	/**
	 * Convert NearEarthObject to smaller representation (FlatNEO)
	 * @param neo NearEarthObject
	 * @return FlatNEO object
	 */
	public FlatNEO convertToFlatNEO(NearEarthObject neo) {
		FlatNEO newFlatNeo = new FlatNEO();
		newFlatNeo.setReferenceId(neo.getNeoReferenceId())
			.setSelfLink(urlHelper.getDetailedNeoInfoURL(neo.getNeoReferenceId()))
			.setName(neo.getName())
			.setAbsoluteMagnitude(neo.getAbsoluteMagnitudeH())
			.setEstimatedDiameterMin(neo.getMeasurements().get(NearEarthObject.KM).getMin())
			.setEstimatedDiameterMax(neo.getMeasurements().get(NearEarthObject.KM).getMax())
			.setPotentiallyHazardousAsteroid(neo.isPotentiallyHazardousAsteroid())
			.setRelativeVelocity(neo.getCloseApproach().get(0).getRelativeVelocity().get(CloseApproachData.KM_PER_SEC))
			.setMissDistance(neo.getCloseApproach().get(0).getMissDistance().get(CloseApproachData.KM))
			.setOrbitingBody(neo.getCloseApproach().get(0).getOrbitingBody());
		return newFlatNeo;
	}
	

	/**
	 * Ascending Sort the NearEarthObjects by size in the given neoContainer
	 * @param neoCont NearEarhtObjet Container
	 * @return List of FlatNEO objects
	 */
	public List<FlatNEO> sortNEOByDistanceToEarth(NearEarthObjectContainer neoCont) {
		//1.Unroll Date1->{NEO1,2,3},Date2->{NEO4,5,,,N} into NEO{1...N}
		//2.sort with a comparator
		//3.map over the sorted collection and convert to FlatNEO
		List<NearEarthObject> flatSet = new ArrayList<NearEarthObject>();
		neoCont.getDateNeoMap().forEach((date,neoList)->flatSet.addAll(neoList));
		Collections.sort(flatSet,NEOComparators.NEO_DISTANCE_COMPARATOR);
		List<FlatNEO> ret= flatSet.stream()
				.map(item -> convertToFlatNEO(item))
				.collect(Collectors.toList());
		return ret;
	}
	
	/**
	 * Descending Sort the NearEarthObjects by size in the given neoContainer
	 * @param neoCont NearEarhtObjet Container
	 * @return List of flat NEO objects
	 */
	public List<FlatNEO> sortNEOBySize(NearEarthObjectContainer neoCont) {
		//1.Unroll Date1->{NEO1,2,3},Date2->{NEO4,5,,,N} into NEO{1...N}
		//2.sort with a comparator
		//3.map over the sorted collection and convert to FlatNEO
		List<NearEarthObject> flatSet = new ArrayList<NearEarthObject>();
		neoCont.getDateNeoMap().forEach((date,neoList)->flatSet.addAll(neoList));
		Collections.sort(flatSet,NEOComparators.NEO_SIZE_COMPARATOR.reversed());
		List<FlatNEO> ret= flatSet.stream()
				.map(item -> convertToFlatNEO(item))
				.collect(Collectors.toList());
		
		return ret;
	}
	
}
