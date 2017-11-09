package com.omiomi.exercises.neo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Store estimated diameter information
 * 
 * @author omi
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimatedDiameter {
	enum UNITS {
		KILOMETERS, METERS, MILES, FEET;
		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	public EstimatedDiameter() {
	}

	@JsonProperty("estimated_diameter_min")
	private float min;

	@JsonProperty("estimated_diameter_max")
	private float max;

	/**
	 * @return the min
	 */
	public float getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(float min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public float getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(float max) {
		this.max = max;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(max);
		result = prime * result + Float.floatToIntBits(min);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstimatedDiameter other = (EstimatedDiameter) obj;
		if (Float.floatToIntBits(max) != Float.floatToIntBits(other.max))
			return false;
		if (Float.floatToIntBits(min) != Float.floatToIntBits(other.min))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EstimatedDiameter [min=" + min + ", max=" + max + "]";
	}
	
	

	
	
}

