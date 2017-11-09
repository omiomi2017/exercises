package com.omiomi.exercises.neo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.domain.NEOJsonGenerator;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.repository.NearEarthObjectDateListingRepository;
public class NearEarthObjectDateListingRepositoryImplTest extends AbstractTest {
	
	@Autowired
	private NearEarthObjectDateListingRepository repository;
	
	@Before
	public void setup() {
		repository.clear();
	}
	
	//Adding adding null date should result in failure
	@Test
	public void setNearEarthObjectListForDate_shouldFail_withNullDate() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		List<NearEarthObject> neolist = new ArrayList<>();
		neolist.add(neo1);
		boolean result = repository.setNearObjectListForDate(null, neolist);
		assertFalse(result);
	}
	
	//Adding null list should result in failure
	@Test
	public void setNearEarthObjectListForDate_shouldFail_withNullList() {
		assertFalse(repository.setNearObjectListForDate("2017-01-01", null));
	}
	
	//Adding a list should be found in the repository
	@Test
	public void newListAddedShouldBeFound() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		List<NearEarthObject> neolist = new ArrayList<>();
		String date = "2017-01-01";
		neolist.add(neo1);
		assertTrue(repository.setNearObjectListForDate(date, neolist));
		List<NearEarthObject> neolistReturned = repository.getNearEarthObjectForDate(date).get();
		assertTrue(neolist.size()==neolistReturned.size());
		assertTrue(neolist.containsAll(neolistReturned));
	}
	
	//Adding the same NEO multiple times should result in only one NEO (for the given date)
	@Test
	public void addingSameObjectMultipleTimes_shouldProduceOnlyOneObj() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		String date = "2017-01-02";
		int currSize = repository.getNearEarthObjectForDate(date)
				.orElse(new ArrayList<NearEarthObject>()).size();
		repository.addNearEarthObjectForDate(date, neo1);
		repository.addNearEarthObjectForDate(date, neo1);
		repository.addNearEarthObjectForDate(date, neo1);
		repository.addNearEarthObjectForDate(date, neo1);
		repository.addNearEarthObjectForDate(date, neo1);
		int newSize = repository.getNearEarthObjectForDate(date).get().size();
		assertTrue(newSize==currSize+1);
	}
	
	//Adding a valid NearEarthObject should fail adding for a nonsensical date
		@Test
		public void failGivenNonsensicalDate() {
			NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
			String date = "2017-34-45";
			assertFalse(repository.addNearEarthObjectForDate(date, neo1));
		}
}
