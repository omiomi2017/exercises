package com.omiomi.exercises.neo.domain;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omiomi.exercises.neo.AbstractTest;
import com.omiomi.exercises.neo.domain.CloseApproachData;
import com.omiomi.exercises.neo.domain.NEOJsonGenerator;

public class CloseApproachDataTest extends AbstractTest {
	private static CloseApproachData cad;
	//parse Close approach JSON data.
	@BeforeClass
	public static void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		cad = om.readValue(NEOJsonGenerator.CLOSE_APPROACH_DATA_JSON, CloseApproachData.class);
	}
	
	@Test
	public void closeApproachObjectMatchesExpected() {
		assertEquals(NEOJsonGenerator.getCloseApproachSample(), cad);
	}
	
	@Test
	public void equalsSelf() {
		assertTrue(cad.equals(cad));
	}
	
}
