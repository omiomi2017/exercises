package com.omiomi.exercises.neo.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;
import com.omiomi.exercises.neo.util.DateCheck;
import com.omiomi.exercises.neo.util.NEOClientUtils;

@Repository
public class NearEarthObjectDateListingRepositoryImpl implements NearEarthObjectDateListingRepository {
	
	@Autowired private NEOClientUtils neoUtils;
	
	private static final Map<String, Map<Integer, NearEarthObject>> map;
	static {
		map = new ConcurrentSkipListMap <>();
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Optional<List<NearEarthObject>> getNearEarthObjectForDate(String date) {
		logger.debug("getNearEarthObjectForDate {}",date);
		if(!DateCheck.isISODate(date) || !map.containsKey(date)) return Optional.empty();
		return Optional.of(map.get(date)
				.entrySet()
				.stream()
				.map(e->e.getValue())
				.collect(Collectors.toList()));
	}
	

	@Override
	public boolean setNearObjectListForDate(String date, Collection<NearEarthObject> neoList) {
		logger.debug("setNearObjectListForDate {}, {}",date,neoList);
		if(date==null || neoList==null) return false;
		if(!DateCheck.isISODate(date)) return false;
		map.putIfAbsent(date, new ConcurrentSkipListMap<Integer, NearEarthObject>());
		map.get(date).putAll(
				neoList.stream()
				.collect(Collectors
						.toMap(item->item.getNeoReferenceId(), Function.identity()
								)
						)
				);
		return true;
	}

	@Override
	public boolean containsDataForDate(String date) {
		logger.debug("containsDataForDate {}", date);
		if(date==null || !DateCheck.isISODate(date)) return false;
		return map.getOrDefault(date, new ConcurrentSkipListMap<Integer, NearEarthObject>()).isEmpty();
	}

	@Override
	public boolean addNearEarthObjectForDate(String date, NearEarthObject newNeo) {
		logger.debug("addNearEarthObjectForDate {} {}", date,newNeo);
		if(date==null || newNeo==null) return false;
		if(!DateCheck.isISODate(date)) return false;
		map.putIfAbsent(date, new ConcurrentSkipListMap<Integer, NearEarthObject>());
		map.get(date).put(newNeo.getNeoReferenceId(), newNeo);
		return true;
	}

	@Override
	public boolean containsNearEarthObjectForDate(String date, NearEarthObject newNeo) {
		logger.debug("containsNearEarthObjectForDate {} {}",date, newNeo);
		if(date==null || newNeo==null) return false;
		//if map.get(date) is null that must imply that addNearEarthObjectForDate method is wrong!!
		return map.containsKey(date) && map.get(date).containsValue(newNeo);
	}

	@Override
	public Optional<NearEarthObject> getNearEarthObjectByDateAndID(String date, Integer neoID) {
		logger.debug("getNearEarthObjectByDateAndID {} {}",date, neoID);
		return Optional.of(map.get(date).get(neoID));
	}


	@Override
	public long size() {
		return map.values()
				.stream()
				.map(e->e.size())
				.reduce(0, Integer::sum);
	}


	@Override
	public void clear() {
		map.clear();
	}


	@Override
	public boolean addAll(NearEarthObjectContainer neoCont) {
		logger.debug("addAll {}",neoCont);
		neoCont.getDateNeoMap().forEach((date,neoSet) ->{
			addAll(date,neoSet);
		});
		return true; //TODO: do some pre-verification of keys, can be potentially broken
	}
	
	@Override
	public boolean addAll(String date, Collection<NearEarthObject> neoValues) {
		logger.debug("date",date,neoValues);
		neoValues.forEach(item -> addNearEarthObjectForDate(date, item));
		return true; //TODO: do some pre-verification of keys, can be potentially broken
	}

}
