package com.omiomi.exercises.neo.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.domain.NEOJsonGenerator;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.repository.NearEarthObjectRepository;

/**
 * Tests NEO repository
 * (this one is for detailed NEO listing)
 * @author omi
 *
 */
public class NearEarthObjectRepositoryImplTest extends AbstractTest{
	
	@Autowired
	private NearEarthObjectRepository repository;
	
	@Before
	public void setup() {
		repository.clear();
	}
	
	@Test
	public void testSizeConstant_whenGetManyTimes() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		assertTrue(repository.addNEO(neo));
		long oldSize=repository.size();
		for(int i=0;i<100;i++) {
			NearEarthObject retrievedValue = repository.getNEOById(neo.getNeoReferenceId()).get();
			assertTrue(repository.size() == oldSize);
		}
	}
	
	// Test clear methods
	@Test
	public void testClear() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		assertTrue(repository.addNEO(neo));
		assertTrue(repository.size()==1);
		repository.clear();
		assertTrue(repository.size()==0);
	}
	
	//Test looking up by ID returns same object
	@Test 
	public void testLookupByIDReturnsSameObject() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		Integer neoID = neo.getNeoReferenceId();
		assertTrue(repository.addNEO(neo));
		assertTrue(repository.getNEOById(neoID).get().equals(neo));
	}
	
	//test adding identical objects results in size one
	@Test
	public void shouldHaveOneEntry_afterIdenticalObjInsertion() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		assertTrue(repository.addNEO(neo));
		for(int i=0;i<10;i++) repository.addNEO(neo);
		assertTrue(repository.size()==1);
	}
}
