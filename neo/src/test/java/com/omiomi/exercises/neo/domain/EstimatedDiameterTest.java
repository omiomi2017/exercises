package com.omiomi.exercises.neo.domain;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.domain.EstimatedDiameter;

public class EstimatedDiameterTest extends AbstractTest {

	private static EstimatedDiameter ed;
	
	@BeforeClass
	public static void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		ed = om.readValue(NEOJsonGenerator.ESTIMATED_DIAMETER_KM, EstimatedDiameter.class);
	}
	
	@Test
	public void estimatedDiameterParsed() {
		assertNotNull(ed);
	}
	
	@Test
	public void estimatedDiameterSampleMatchesExpected() {
		assertEquals(ed, NEOJsonGenerator.getEstimatedDiameterSampleInKM());
	}
	
	@Test
	public void equalsSelf() {
		assertTrue(ed.equals(ed));
	}

}
