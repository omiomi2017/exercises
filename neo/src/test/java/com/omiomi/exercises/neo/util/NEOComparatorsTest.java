package com.omiomi.exercises.neo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.omiomi.exercises.neo.domain.CloseApproachData;
import com.omiomi.exercises.neo.domain.EstimatedDiameter;
import com.omiomi.exercises.neo.domain.NEOJsonGenerator;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.util.NEOComparators;

/**
 * Test NEOComparator methods
 * @author omi
 *
 */
public class NEOComparatorsTest {
	
	/**
	 * Function returns NearEarthObject with 1/2 the approach distance (we only care about KM)
	 * @return NEO with 1/2 of the sample distance to Earth specification
	 */
	private NearEarthObject getHalfDistanceNEO() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		Map<String,Float> missDistances = new LinkedHashMap<String, Float>(neo.getCloseApproach().get(0).getMissDistance());
		float halfNeo1Distance = NEOComparators.getDistance(neo)/2;
		missDistances.put(CloseApproachData.KM, halfNeo1Distance);
		neo.getCloseApproach().get(0).setMissDistance(missDistances);
		return neo;
	}
	
	/**
	 * Function to get an even smaller Earth<>NEO distance(quarter-size lets call it)
	 * @return NEO with 1/4 of the sample distance to Earth specification
	 */
	private NearEarthObject getQuarterDistanceNEO() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		Map<String,Float> missDistances = new LinkedHashMap<String, Float>(neo.getCloseApproach().get(0).getMissDistance());
		float halfNeo1Distance = NEOComparators.getDistance(neo)/4;
		missDistances.put(CloseApproachData.KM, halfNeo1Distance);
		neo.getCloseApproach().get(0).setMissDistance(missDistances);
		return neo;
	}
	
	
	private NearEarthObject getHalfSizeNEO() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		Map<String, EstimatedDiameter> newMeasurements = new LinkedHashMap<String, EstimatedDiameter>(neo.getMeasurements());
		EstimatedDiameter inKM = newMeasurements.get(NearEarthObject.KM);
		inKM.setMax(inKM.getMax()/2);
		inKM.setMin(inKM.getMax()/2);
		neo.setMeasurements(newMeasurements);
		return neo;
	}
	
	private NearEarthObject getQuarterSizeNEO() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		Map<String, EstimatedDiameter> newMeasurements = new LinkedHashMap<String, EstimatedDiameter>(neo.getMeasurements());
		EstimatedDiameter inKM = newMeasurements.get(NearEarthObject.KM);
		inKM.setMax(inKM.getMax()/4);
		inKM.setMin(inKM.getMax()/4);
		neo.setMeasurements(newMeasurements);
		return neo;
	}
	
	/**
	 * NearEarthObject distance extractor test.
	 */
	@Test
	public void testDistanceExtraction() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		Float expectedDistance = neo.getCloseApproach().get(0).getMissDistance().get(CloseApproachData.KM);
		assertEquals(expectedDistance, NEOComparators.getDistance(neo));
	}
	
	/**
	 * NearEarthObject size extractor test.
	 */
	@Test
	public void testSizeExtraction() {
		NearEarthObject neo = NEOJsonGenerator.getNEOSample();
		EstimatedDiameter ed = neo.getMeasurements().get(NearEarthObject.KM);
		Float expectedSize = (ed.getMin()+ed.getMax() )/2;
		assertEquals(expectedSize, NEOComparators.getSize(neo));
	}
	
	/**
	 * Test distance comparator on equal objects
	 */
	@Test
	public void testDistanceComparator_equalObjects() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		NearEarthObject neo2 = NEOJsonGenerator.getNEOSample();
		int expectedValue = 0; //equal
		assertEquals(expectedValue, 
				NEOComparators.NEO_DISTANCE_COMPARATOR.compare(neo1, neo2));
	}
	
	@Test
	public void testDistanceComparator_leftSmaller() {
		NearEarthObject neo1 = getHalfDistanceNEO();
		NearEarthObject neo2 = NEOJsonGenerator.getNEOSample();
		assertTrue(0>NEOComparators.NEO_DISTANCE_COMPARATOR.compare(neo1, neo2));
	}
	
	@Test
	public void testDistanceComparator_rightSmaller() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		NearEarthObject neo2 = getHalfDistanceNEO();
		assertTrue(0<NEOComparators.NEO_DISTANCE_COMPARATOR.compare(neo1, neo2));
	}
	
	/**
	 * Testing Distance Comparator sign<>comparisonDirection reversal
	 * sgn(compare(x, y)) == -sgn(compare(y, x)) for all x and y
	 */
	@Test
	public void testDistanceComparatorSignReversal() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		NearEarthObject neo2 = getHalfDistanceNEO();
		int leftSide  = NEOComparators.NEO_DISTANCE_COMPARATOR.compare(neo1, neo2);
		int rightSide = NEOComparators.NEO_DISTANCE_COMPARATOR.compare(neo2, neo1);
		assertEquals(-1*leftSide, rightSide);
	}
	
	/**Testing Distance Comparator transitive property
	 * ((compare(x, y)>0) && (compare(y, z)>0)) implies compare(x, z)>0
	 */
	@Test
	public void testDistanceComparator_Transitivity() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		NearEarthObject neo2 = getHalfDistanceNEO();
		NearEarthObject neo3 = getQuarterDistanceNEO();
		
		boolean leftSide = (NEOComparators.NEO_DISTANCE_COMPARATOR.compare(neo1,neo2)>0) &&
							(NEOComparators.NEO_DISTANCE_COMPARATOR.compare(neo1,neo2)>0);
		boolean rightSide = NEOComparators.NEO_DISTANCE_COMPARATOR.compare(neo1,neo3)>0;
		//here we're modeling an implication
		assumeTrue(leftSide); // the test will be ignored if left side is invalid
		assertTrue(rightSide); //then the right side must follow.
	}
	
	
	
	
	/****** NOW VERIFY SIZE COMPARATOR******/
	
	
	/**
	 * Test size comparator on equal objects
	 */
	@Test
	public void tesSizeComparator_equalObjects() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		NearEarthObject neo2 = NEOJsonGenerator.getNEOSample();
		int expectedValue = 0; //equal
		assertEquals(expectedValue, 
				NEOComparators.NEO_SIZE_COMPARATOR.compare(neo1, neo2));
	}
	

	@Test
	public void testSizeComparator_leftSmaller() {
		NearEarthObject neo1 = getHalfSizeNEO();
		NearEarthObject neo2 = NEOJsonGenerator.getNEOSample();
		assertTrue(0>NEOComparators.NEO_SIZE_COMPARATOR.compare(neo1, neo2));
	}
	
	@Test
	public void testSizeComparator_RightSmaller() {
		NearEarthObject neo2 = getHalfSizeNEO();
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		assertTrue(0<NEOComparators.NEO_SIZE_COMPARATOR.compare(neo1, neo2));
	}
	

	/**
	 * Testing Size Comparator sign<>comparisonDirection reversal
	 * sgn(compare(x, y)) == -sgn(compare(y, x)) for all x and y
	 */
	@Test
	public void testSizeComparatorSignReversal() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		NearEarthObject neo2 = getHalfSizeNEO();
		int leftSide  = NEOComparators.NEO_SIZE_COMPARATOR.compare(neo1, neo2);
		int rightSide = NEOComparators.NEO_SIZE_COMPARATOR.compare(neo2, neo1);
		assertEquals(-1*leftSide, rightSide);
	}
	
	/**Testing Size Comparator transitive property
	 * ((compare(x, y)>0) && (compare(y, z)>0)) implies compare(x, z)>0
	 */
	@Test
	public void testSizeComparator_Transitivity() {
		NearEarthObject neo1 = NEOJsonGenerator.getNEOSample();
		NearEarthObject neo2 = getHalfSizeNEO();
		NearEarthObject neo3 = getQuarterSizeNEO();
		
		boolean leftSide = (NEOComparators.NEO_SIZE_COMPARATOR.compare(neo1,neo2)>0) &&
							(NEOComparators.NEO_SIZE_COMPARATOR.compare(neo1,neo2)>0);
		boolean rightSide = NEOComparators.NEO_SIZE_COMPARATOR.compare(neo1,neo3)>0;
		//here we're modeling an implication
		assumeTrue(leftSide); // the test will be ignored if left side is invalid
		assertTrue(rightSide); //then the right side must follow.
	}
	
}
