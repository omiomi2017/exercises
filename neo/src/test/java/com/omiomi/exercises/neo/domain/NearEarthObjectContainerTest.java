package com.omiomi.exercises.neo.domain;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.domain.NearEarthObjectContainer;

/**
 * Test NearEarthObjectContainer
 * @author omi
 *
 */
public class NearEarthObjectContainerTest extends AbstractTest {
	
	private static NearEarthObjectContainer neoContainer;
	
	@BeforeClass
	public static void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		neoContainer= om.readValue(NEOJsonGenerator.FULL_OBJ_JSON, NearEarthObjectContainer.class);
	}
	
	/**
	 * JSON parsing should not return a null object.
	 */
	@Test
	public void NEOContainerNotNull() {
		assertNotNull(neoContainer);
	}
	
	/**
	 * Parsed number of elements (field) should be 14.
	 */
	@Test
	public void elementCountFieldShouldBe14() {
		assertEquals(neoContainer.getElementCount(),NEOJsonGenerator.FULL_OBJ_NUM_ELEMENTS);
	}
	
	/**
	 * There should also be 14 NEO objects in NEOContainer (nested by different dates)
	 */
	@Test
	public void ThereAre14NEOObjectsInContainer() {
		final long numItems[]= {0};
		neoContainer.getDateNeoMap().forEach((date,neoSet)->{numItems[0]+=neoSet.size();});
		
		assertEquals(numItems[0], NEOJsonGenerator.FULL_OBJ_NUM_ELEMENTS);
	}
	
	@Test
	public void equalsSelf() {
		assertTrue(neoContainer.equals(neoContainer));
	}

}
