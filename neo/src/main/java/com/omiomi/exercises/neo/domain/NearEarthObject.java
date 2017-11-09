package com.omiomi.exercises.neo.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines properties for a NearEarthObject
 * 
 * @author omi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true) // don't care about links and other HATEOAS properness at the moment.
public class NearEarthObject {

	public static final String KM = "kilometers";

	/**
	 * Default constructor
	 */
	public NearEarthObject() {}
	@JsonProperty("neo_reference_id") private int neoReferenceId;
	@JsonProperty("name") private String name;
	@JsonProperty("absolute_magnitude_h") private float absoluteMagnitudeH;
	@JsonProperty("estimated_diameter") private Map<String, EstimatedDiameter> measurements;
	@JsonProperty("is_potentially_hazardous_asteroid") private boolean isPotentiallyHazardousAsteroid;
	@JsonProperty("close_approach_data") private List<CloseApproachData> closeApproach;
	@JsonProperty(required=false, value="orbital_data") private OrbitalData orbitalData;
	
	/**
	 * @return the neoReferenceId
	 */
	public int getNeoReferenceId() {
		return neoReferenceId;
	}

	/**
	 * @param neoReferenceId
	 *            the neoReferenceId to set
	 */
	public void setNeoReferenceId(int neoReferenceId) {
		this.neoReferenceId = neoReferenceId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the absoluteMagnitudeH (which is not height, but relative unabstracted luminocity)
	 */
	public float getAbsoluteMagnitudeH() {
		return absoluteMagnitudeH;
	}

	/**
	 * @param absoluteMagnitudeH
	 *            the absoluteMagnitudeHeight to set
	 */
	public void setAbsoluteMagnitudeH(float absoluteMagnitudeH) {
		this.absoluteMagnitudeH = absoluteMagnitudeH;
	}

	/**
	 * @return the measurements
	 */
	public Map<String, EstimatedDiameter> getMeasurements() {
		return Collections.unmodifiableMap(measurements);
	}

	/**
	 * @param measurements
	 *            the measurements to set
	 */
	public void setMeasurements(Map<String, EstimatedDiameter> measurements) {
		this.measurements = measurements;
	}

	/**
	 * @return the isPotentiallyHazardousAsteroid
	 */
	public boolean isPotentiallyHazardousAsteroid() {
		return isPotentiallyHazardousAsteroid;
	}

	/**
	 * @param isPotentiallyHazardousAsteroid
	 *            the isPotentiallyHazardousAsteroid to set
	 */
	public void setPotentiallyHazardousAsteroid(boolean isPotentiallyHazardousAsteroid) {
		this.isPotentiallyHazardousAsteroid = isPotentiallyHazardousAsteroid;
	}

	/**
	 * @return the closeApproach
	 */
	public List<CloseApproachData> getCloseApproach() {
		return closeApproach;
	}

	/**
	 * @param closeApproach the closeApproach to set
	 */
	public void setCloseApproach(List<CloseApproachData> closeApproach) {
		this.closeApproach = closeApproach;
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
		result = prime * result + Float.floatToIntBits(absoluteMagnitudeH);
		result = prime * result + ((closeApproach == null) ? 0 : closeApproach.hashCode());
		result = prime * result + (isPotentiallyHazardousAsteroid ? 1231 : 1237);
		result = prime * result + ((measurements == null) ? 0 : measurements.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + neoReferenceId;
		result = prime * result + ((orbitalData == null) ? 0 : orbitalData.hashCode());
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
		NearEarthObject other = (NearEarthObject) obj;
		if (Float.floatToIntBits(absoluteMagnitudeH) != Float.floatToIntBits(other.absoluteMagnitudeH))
			return false;
		if (closeApproach == null) {
			if (other.closeApproach != null)
				return false;
		} else if (!closeApproach.equals(other.closeApproach))
			return false;
		if (isPotentiallyHazardousAsteroid != other.isPotentiallyHazardousAsteroid)
			return false;
		if (measurements == null) {
			if (other.measurements != null)
				return false;
		} else if (!measurements.equals(other.measurements))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (neoReferenceId != other.neoReferenceId)
			return false;
		if (orbitalData == null) {
			if (other.orbitalData != null)
				return false;
		} else if (!orbitalData.equals(other.orbitalData))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NearEarthObject [neoReferenceId=" + neoReferenceId + ", name=" + name + ", absoluteMagnitudeHeight="
				+ absoluteMagnitudeH + ", measurements=" + measurements.get("kilometers") + ", isPotentiallyHazardousAsteroid="
				+ isPotentiallyHazardousAsteroid + ", closeApproach=" + closeApproach +  ", orbitalData="+orbitalData+"]\n";
	}

	
}
