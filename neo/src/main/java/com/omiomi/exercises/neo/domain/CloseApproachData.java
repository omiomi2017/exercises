package com.omiomi.exercises.neo.domain;

import java.util.Collections;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stores close approach data.
 * @author omi
 */

@JsonIgnoreProperties(ignoreUnknown = true) //don't care about links and other HATEOAS properness
public class CloseApproachData {
	public CloseApproachData() {}
	//constants for miss distance
	public static final String KM = "kilometers";
	public static final String KM_PER_SEC = "kilometers_per_second";
	

	@JsonProperty("close_approach_date")
	private String closeApproachDate;
	
	@JsonProperty("epoch_date_close_approach")
	private long epochDateCloseApproach;
	
	@JsonProperty("relative_velocity")
	private Map<String,Float> relativeVelocity;
	
	@JsonProperty("miss_distance")
	private Map<String,Float> missDistance;
	
	@JsonProperty("orbiting_body")
	private String orbitingBody;

	/**
	 * @return the closeApproachDate
	 */
	public String getCloseApproachDate() {
		return closeApproachDate;
	}

	/**
	 * @param closeApproachDate the closeApproachDate to set
	 */
	public void setCloseApproachDate(String closeApproachDate) {
		this.closeApproachDate = closeApproachDate;
	}

	/**
	 * @return the epochDateCloseApproach
	 */
	public long getEpochDateCloseApproach() {
		return epochDateCloseApproach;
	}

	/**
	 * @param epochDateCloseApproach the epochDateCloseApproach to set
	 */
	public void setEpochDateCloseApproach(long epochDateCloseApproach) {
		this.epochDateCloseApproach = epochDateCloseApproach;
	}

	/**
	 * @return the relativeVelocity
	 */
	public Map<String, Float> getRelativeVelocity() {
		return Collections.unmodifiableMap(relativeVelocity);
	}

	/**
	 * @param relativeVelocity the relativeVelocity to set
	 */
	public void setRelativeVelocity(Map<String, Float> relativeVelocity) {
		this.relativeVelocity = relativeVelocity;
	}

	/**
	 * @return the missDistance
	 */
	public Map<String, Float> getMissDistance() {
		return Collections.unmodifiableMap(missDistance);
	}

	/**
	 * @param missDistance the missDistance to set
	 */
	public void setMissDistance(Map<String, Float> missDistance) {
		this.missDistance = missDistance;
	}

	/**
	 * @return the orbitingBody
	 */
	public String getOrbitingBody() {
		return orbitingBody;
	}

	/**
	 * @param orbitingBody the orbitingBody to set
	 */
	public void setOrbitingBody(String orbitingBody) {
		this.orbitingBody = orbitingBody;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closeApproachDate == null) ? 0 : closeApproachDate.hashCode());
		result = prime * result + (int) (epochDateCloseApproach ^ (epochDateCloseApproach >>> 32));
		result = prime * result + ((missDistance == null) ? 0 : missDistance.hashCode());
		result = prime * result + ((orbitingBody == null) ? 0 : orbitingBody.hashCode());
		result = prime * result + ((relativeVelocity == null) ? 0 : relativeVelocity.hashCode());
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
		CloseApproachData other = (CloseApproachData) obj;
		if (closeApproachDate == null) {
			if (other.closeApproachDate != null)
				return false;
		} else if (!closeApproachDate.equals(other.closeApproachDate))
			return false;
		if (epochDateCloseApproach != other.epochDateCloseApproach)
			return false;
		if (missDistance == null) {
			if (other.missDistance != null)
				return false;
		} else if (!missDistance.equals(other.missDistance))
			return false;
		if (orbitingBody == null) {
			if (other.orbitingBody != null)
				return false;
		} else if (!orbitingBody.equals(other.orbitingBody))
			return false;
		if (relativeVelocity == null) {
			if (other.relativeVelocity != null)
				return false;
		} else if (!relativeVelocity.equals(other.relativeVelocity))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CloseApproachData [closeApproachDate=" + closeApproachDate + ", epochDateCloseApproach="
				+ epochDateCloseApproach + ", relativeVelocity=" + relativeVelocity + ", missDistance=" + missDistance
				+ ", orbitingBody=" + orbitingBody + "]";
	}
	
	
}
