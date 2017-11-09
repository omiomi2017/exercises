package com.omiomi.exercises.neo.domain;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.domain.NearEarthObject;

public class NearEarthObjectTest extends AbstractTest {

	private static NearEarthObject neo;
	
	@BeforeClass
	public static void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		neo = om.readValue(NEOJsonGenerator.NEAR_EARTH_OBJECT_JSON, NearEarthObject.class);
	}
	
	@Test
	public void NearEarthObjectParsed() {
		assertNotNull(neo);
	}
	
	@Test
	public void NearEarthOBjectParsedCorrectly() {
		assertEquals(NEOJsonGenerator.getNEOSample(), neo);
	}

	@Test
	public void equalsSelf() {
		assertTrue(neo.equals(neo));
	}
}
