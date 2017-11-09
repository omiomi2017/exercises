package com.omiomi.exercises.neo.domain;

import java.io.Serializable;

/**
 * Stores flattened NearEarthObject, will be sent to REST client
 * 
 * @author omi
 */
public class FlatNEO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 12345L;
	
	private String selfLink; // link to get more orbital information from.
	private int referenceId; //each NEO has an integer ID
	private String name; //name usually in round brackets
	private float absoluteMagnitude; // not clear on units
	private float estimatedDiameterMin; // we'll be storing result in meters only
	private float estimatedDiameterMax; // stored in meters
	private boolean isPotentiallyHazardousAsteroid; //What is love?
	private float relativeVelocity; // we'll store KM/sec value
	private float missDistance; // we'll store distance in KM
	private String orbitingBody;  //Usually Earth
	private OrbitalData orbitalData;  //some more nested data
	/**
	 * Default constructor
	 */
	public FlatNEO() {}
	
		/**
	 * @return the orbitingBody
	 */
	public String getOrbitingBody() {
		return orbitingBody;
	}

	/**
	 * @param orbitingBody the orbitingBody to set
	 */
	public FlatNEO setOrbitingBody(String orbitingBody) {
		this.orbitingBody = orbitingBody;
		return this;
	}

		/**
	 * @return the selfLink
	 */
	public String getSelfLink() {
		return selfLink;
	}
	/**
	 * @param selfLink the selfLink to set
	 */
	public FlatNEO setSelfLink(String selfLink) {
		this.selfLink = selfLink;
		return this;
	}
	/**
	 * @return the referenceId
	 */
	public int getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId the referenceId to set
	 */
	public FlatNEO setReferenceId(int referenceId) {
		this.referenceId = referenceId;
		return this;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public FlatNEO setName(String name) {
		this.name = name;
		return this;
	}
	/**
	 * @return the absoluteMagnitude
	 */
	public float getAbsoluteMagnitude() {
		return absoluteMagnitude;
	}
	/**
	 * @param absoluteMagnitude the absoluteMagnitude to set
	 */
	public FlatNEO setAbsoluteMagnitude(float absoluteMagnitude) {
		this.absoluteMagnitude = absoluteMagnitude;
		return this;
	}
	/**
	 * @return the estimatedDiameterMin
	 */
	public float getEstimatedDiameterMin() {
		return estimatedDiameterMin;
	}
	/**
	 * @param estimatedDiameterMin the estimatedDiameterMin to set
	 */
	public FlatNEO setEstimatedDiameterMin(float estimatedDiameterMin) {
		this.estimatedDiameterMin = estimatedDiameterMin;
		return this;
	}
	/**
	 * @return the estimatedDiameterMax
	 */
	public float getEstimatedDiameterMax() {
		return estimatedDiameterMax;
	}
	/**
	 * @param estimatedDiameterMax the estimatedDiameterMax to set
	 */
	public FlatNEO setEstimatedDiameterMax(float estimatedDiameterMax) {
		this.estimatedDiameterMax = estimatedDiameterMax;
		return this;
	}
	/**
	 * @return the isPotentiallyHazardousAsteroid
	 */
	public boolean isPotentiallyHazardousAsteroid() {
		return isPotentiallyHazardousAsteroid;
	}
	/**
	 * @param isPotentiallyHazardousAsteroid the isPotentiallyHazardousAsteroid to set
	 */
	public FlatNEO setPotentiallyHazardousAsteroid(boolean isPotentiallyHazardousAsteroid) {
		this.isPotentiallyHazardousAsteroid = isPotentiallyHazardousAsteroid;
		return this;
	}
	/**
	 * @return the relativeVelocity
	 */
	public float getRelativeVelocity() {
		return relativeVelocity;
	}
	/**
	 * @param relativeVelocity the relativeVelocity to set
	 */
	public FlatNEO setRelativeVelocity(float relativeVelocity) {
		this.relativeVelocity = relativeVelocity;
		return this;
	}
	/**
	 * @return the missDistance
	 */
	public float getMissDistance() {
		return missDistance;
	}
	/**
	 * @param missDistance the missDistance to set
	 */
	public FlatNEO setMissDistance(float missDistance) {
		this.missDistance = missDistance;
		return this;
	}

	/**
	 * @return the orbitalData
	 */
	public OrbitalData getOrbitalData() {
		return orbitalData;
	}

	/**
	 * @param orbitalData the orbitalData to set
	 */
	public void setOrbitalData(OrbitalData orbitalData) {
		this.orbitalData = orbitalData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(absoluteMagnitude);
		result = prime * result + Float.floatToIntBits(estimatedDiameterMax);
		result = prime * result + Float.floatToIntBits(estimatedDiameterMin);
		result = prime * result + (isPotentiallyHazardousAsteroid ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(missDistance);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orbitalData == null) ? 0 : orbitalData.hashCode());
		result = prime * result + ((orbitingBody == null) ? 0 : orbitingBody.hashCode());
		result = prime * result + referenceId;
		result = prime * result + Float.floatToIntBits(relativeVelocity);
		result = prime * result + ((selfLink == null) ? 0 : selfLink.hashCode());
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
		FlatNEO other = (FlatNEO) obj;
		if (Float.floatToIntBits(absoluteMagnitude) != Float.floatToIntBits(other.absoluteMagnitude))
			return false;
		if (Float.floatToIntBits(estimatedDiameterMax) != Float.floatToIntBits(other.estimatedDiameterMax))
			return false;
		if (Float.floatToIntBits(estimatedDiameterMin) != Float.floatToIntBits(other.estimatedDiameterMin))
			return false;
		if (isPotentiallyHazardousAsteroid != other.isPotentiallyHazardousAsteroid)
			return false;
		if (Float.floatToIntBits(missDistance) != Float.floatToIntBits(other.missDistance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orbitalData == null) {
			if (other.orbitalData != null)
				return false;
		} else if (!orbitalData.equals(other.orbitalData))
			return false;
		if (orbitingBody == null) {
			if (other.orbitingBody != null)
				return false;
		} else if (!orbitingBody.equals(other.orbitingBody))
			return false;
		if (referenceId != other.referenceId)
			return false;
		if (Float.floatToIntBits(relativeVelocity) != Float.floatToIntBits(other.relativeVelocity))
			return false;
		if (selfLink == null) {
			if (other.selfLink != null)
				return false;
		} else if (!selfLink.equals(other.selfLink))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlatNEO [selfLink=" + selfLink + ", referenceId=" + referenceId + ", name=" + name
				+ ", absoluteMagnitude=" + absoluteMagnitude + ", estimatedDiameterMin=" + estimatedDiameterMin
				+ ", estimatedDiameterMax=" + estimatedDiameterMax + ", isPotentiallyHazardousAsteroid="
				+ isPotentiallyHazardousAsteroid + ", relativeVelocity=" + relativeVelocity + ", missDistance="
				+ missDistance + ", orbitingBody=" + orbitingBody + ", orbitalData=" + orbitalData + "]";
	}

	
	
}
