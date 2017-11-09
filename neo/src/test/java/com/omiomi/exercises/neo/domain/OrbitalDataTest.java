package com.omiomi.exercises.neo.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omiomi.exercises.neo.domain.OrbitalData;

/**
 * Make sure we can parse orbital data
 * @author omi
 *
 */
public class OrbitalDataTest {

	private static OrbitalData ob;
	
	@BeforeClass
	public static void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		ob = om.readValue(NEOJsonGenerator.ORBITAL_DATA_JSON, OrbitalData.class);
	}
	
	@Test
	public void NearEarthObjectParsed() {
		assertNotNull(ob);
	}
	
	@Test
	public void NearEarthOBjectParsedCorrectly() {
		assertEquals(NEOJsonGenerator.getOribalDataSample(), ob);
	}

	@Test
	public void equalsSelf() {
		assertTrue(ob.equals(ob));
	}
}
