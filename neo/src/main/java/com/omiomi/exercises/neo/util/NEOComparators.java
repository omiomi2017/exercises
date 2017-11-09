package com.omiomi.exercises.neo.util;

import java.util.Comparator;

import com.omiomi.exercises.neo.domain.CloseApproachData;
import com.omiomi.exercises.neo.domain.EstimatedDiameter;
import com.omiomi.exercises.neo.domain.NearEarthObject;

/**
 * Present comparators for distance and size.
 * 
 * @author omi
 *
 */
public class NEOComparators {

	/**
	 * Extract distance measure (in KM) from a near earth object
	 * @param neo Near Earth OBject
	 * @return distance in KM from Earth
	 */
	public static Float getDistance(NearEarthObject neo) {
		return neo.getCloseApproach().get(0).getMissDistance().get(CloseApproachData.KM);
	}

	/**
	 * Extract approximate size in KM by averaging min and max measurement
	 * @param neo Near Earth Object
	 * @return approximate near earth object size in KM
	 */
	public static Float getSize(NearEarthObject neo) {
		EstimatedDiameter ed = neo.getMeasurements().get(NearEarthObject.KM);
		return (ed.getMin() + ed.getMax()) / 2;
	}

	/**
	 * Near earth distance comparator.
	 * Will be used in sorting.
	 */
	public static final Comparator<NearEarthObject> NEO_DISTANCE_COMPARATOR = new Comparator<NearEarthObject>() {
		@Override
		public int compare(NearEarthObject a, NearEarthObject b) {
			return getDistance(a).compareTo(getDistance(b));
		}
	};

	/**
	 * Near earth object size comparator.
	 * Will be used in sorting.
	 */
	public static final Comparator<NearEarthObject> NEO_SIZE_COMPARATOR = new Comparator<NearEarthObject>() {
		@Override
		public int compare(NearEarthObject a, NearEarthObject b) {
			return getSize(a).compareTo(getSize(b));
		}
	};
}
