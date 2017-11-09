package com.omiomi.exercises.neo.repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

import com.omiomi.exercises.neo.domain.NearEarthObject;
/**
 * Implementation of NearEarthObjectRepository
 * @author omi
 *
 */
@Repository
public class NearEarthObjectRepositoryImpl implements NearEarthObjectRepository {
	
	private static final ConcurrentHashMap<Integer,NearEarthObject> map;
	static {
		map = new ConcurrentHashMap<>();
	}

	@Override
	public Optional<NearEarthObject> getNEOById(Integer id) {
		return Optional.ofNullable(map.get(id));
	}

	@Override
	public boolean containsNEOWithID(Integer id) {
		return map.containsKey(id);
	}

	@Override
	public boolean addNEO(NearEarthObject neo) {
		if(neo == null) return false;
		map.put(neo.getNeoReferenceId(), neo);
		return true;
	}

	@Override
	public boolean containsNEO(NearEarthObject neo) {
		return map.containsValue(neo);
	}

	@Override
	public long size() {return map.size();}

	@Override
	public void clear() {
		map.clear();
	}

}
