package com.omiomi.exercises.neo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stores orbital data for a Near Earth Object
 * @author omi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrbitalData {
	@JsonProperty("orbit_id") private int orbit_id;
	@JsonProperty("orbit_determination_date") private String orbit_determination_date;
	@JsonProperty("orbit_uncertainty") private int orbit_uncertainty;
	@JsonProperty("minimum_orbit_intersection") private float minimum_orbit_intersection;
	@JsonProperty("jupiter_tisserand_invariant") private float jupiter_tisserand_invariant;
	@JsonProperty("epoch_osculation") private float epoch_osculation;
	@JsonProperty("eccentricity") private float eccentricity;
	@JsonProperty("semi_major_axis") private float semi_major_axis;
	@JsonProperty("inclination") private float inclination;
	@JsonProperty("ascending_node_longitude") private float ascending_node_longitude;
	@JsonProperty("orbital_period") private float orbital_period;
	@JsonProperty("perihelion_distance") private float perihelion_distance;
	@JsonProperty("perihelion_argument") private float perihelion_argument;
	@JsonProperty("aphelion_distance") private float aphelion_distance;
	@JsonProperty("perihelion_time") private float perihelion_time;
	@JsonProperty("mean_anomaly") private float mean_anomaly;
	@JsonProperty("mean_motion") private float mean_motion;
	@JsonProperty("equinox") private String equinox;
	
	/**
	 * Default constructor
	 */
	public OrbitalData() {}

	/**
	 * @return the orbit_id
	 */
	public int getOrbit_id() {
		return orbit_id;
	}

	/**
	 * @param orbit_id the orbit_id to set
	 */
	public void setOrbit_id(int orbit_id) {
		this.orbit_id = orbit_id;
	}

	/**
	 * @return the orbit_determination_date
	 */
	public String getOrbit_determination_date() {
		return orbit_determination_date;
	}

	/**
	 * @param orbit_determination_date the orbit_determination_date to set
	 */
	public void setOrbit_determination_date(String orbit_determination_date) {
		this.orbit_determination_date = orbit_determination_date;
	}

	/**
	 * @return the orbit_uncertainty
	 */
	public int getOrbit_uncertainty() {
		return orbit_uncertainty;
	}

	/**
	 * @param orbit_uncertainty the orbit_uncertainty to set
	 */
	public void setOrbit_uncertainty(int orbit_uncertainty) {
		this.orbit_uncertainty = orbit_uncertainty;
	}

	/**
	 * @return the minimum_orbit_intersection
	 */
	public float getMinimum_orbit_intersection() {
		return minimum_orbit_intersection;
	}

	/**
	 * @param minimum_orbit_intersection the minimum_orbit_intersection to set
	 */
	public void setMinimum_orbit_intersection(float minimum_orbit_intersection) {
		this.minimum_orbit_intersection = minimum_orbit_intersection;
	}

	/**
	 * @return the jupiter_tisserand_invariant
	 */
	public float getJupiter_tisserand_invariant() {
		return jupiter_tisserand_invariant;
	}

	/**
	 * @param jupiter_tisserand_invariant the jupiter_tisserand_invariant to set
	 */
	public void setJupiter_tisserand_invariant(float jupiter_tisserand_invariant) {
		this.jupiter_tisserand_invariant = jupiter_tisserand_invariant;
	}

	/**
	 * @return the epoch_osculation
	 */
	public float getEpoch_osculation() {
		return epoch_osculation;
	}

	/**
	 * @param epoch_osculation the epoch_osculation to set
	 */
	public void setEpoch_osculation(float epoch_osculation) {
		this.epoch_osculation = epoch_osculation;
	}

	/**
	 * @return the eccentricity
	 */
	public float getEccentricity() {
		return eccentricity;
	}

	/**
	 * @param eccentricity the eccentricity to set
	 */
	public void setEccentricity(float eccentricity) {
		this.eccentricity = eccentricity;
	}

	/**
	 * @return the semi_major_axis
	 */
	public float getSemi_major_axis() {
		return semi_major_axis;
	}

	/**
	 * @param semi_major_axis the semi_major_axis to set
	 */
	public void setSemi_major_axis(float semi_major_axis) {
		this.semi_major_axis = semi_major_axis;
	}

	/**
	 * @return the inclination
	 */
	public float getInclination() {
		return inclination;
	}

	/**
	 * @param inclination the inclination to set
	 */
	public void setInclination(float inclination) {
		this.inclination = inclination;
	}

	/**
	 * @return the ascending_node_longitude
	 */
	public float getAscending_node_longitude() {
		return ascending_node_longitude;
	}

	/**
	 * @param ascending_node_longitude the ascending_node_longitude to set
	 */
	public void setAscending_node_longitude(float ascending_node_longitude) {
		this.ascending_node_longitude = ascending_node_longitude;
	}

	/**
	 * @return the orbital_period
	 */
	public float getOrbital_period() {
		return orbital_period;
	}

	/**
	 * @param orbital_period the orbital_period to set
	 */
	public void setOrbital_period(float orbital_period) {
		this.orbital_period = orbital_period;
	}

	/**
	 * @return the perihelion_distance
	 */
	public float getPerihelion_distance() {
		return perihelion_distance;
	}

	/**
	 * @param perihelion_distance the perihelion_distance to set
	 */
	public void setPerihelion_distance(float perihelion_distance) {
		this.perihelion_distance = perihelion_distance;
	}

	/**
	 * @return the perihelion_argument
	 */
	public float getPerihelion_argument() {
		return perihelion_argument;
	}

	/**
	 * @param perihelion_argument the perihelion_argument to set
	 */
	public void setPerihelion_argument(float perihelion_argument) {
		this.perihelion_argument = perihelion_argument;
	}

	/**
	 * @return the aphelion_distance
	 */
	public float getAphelion_distance() {
		return aphelion_distance;
	}

	/**
	 * @param aphelion_distance the aphelion_distance to set
	 */
	public void setAphelion_distance(float aphelion_distance) {
		this.aphelion_distance = aphelion_distance;
	}

	/**
	 * @return the perihelion_time
	 */
	public float getPerihelion_time() {
		return perihelion_time;
	}

	/**
	 * @param perihelion_time the perihelion_time to set
	 */
	public void setPerihelion_time(float perihelion_time) {
		this.perihelion_time = perihelion_time;
	}

	/**
	 * @return the mean_anomaly
	 */
	public float getMean_anomaly() {
		return mean_anomaly;
	}

	/**
	 * @param mean_anomaly the mean_anomaly to set
	 */
	public void setMean_anomaly(float mean_anomaly) {
		this.mean_anomaly = mean_anomaly;
	}

	/**
	 * @return the mean_motion
	 */
	public float getMean_motion() {
		return mean_motion;
	}

	/**
	 * @param mean_motion the mean_motion to set
	 */
	public void setMean_motion(float mean_motion) {
		this.mean_motion = mean_motion;
	}

	/**
	 * @return the equinox
	 */
	public String getEquinox() {
		return equinox;
	}

	/**
	 * @param equinox the equinox to set
	 */
	public void setEquinox(String equinox) {
		this.equinox = equinox;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(aphelion_distance);
		result = prime * result + Float.floatToIntBits(ascending_node_longitude);
		result = prime * result + Float.floatToIntBits(eccentricity);
		result = prime * result + Float.floatToIntBits(epoch_osculation);
		result = prime * result + ((equinox == null) ? 0 : equinox.hashCode());
		result = prime * result + Float.floatToIntBits(inclination);
		result = prime * result + Float.floatToIntBits(jupiter_tisserand_invariant);
		result = prime * result + Float.floatToIntBits(mean_anomaly);
		result = prime * result + Float.floatToIntBits(mean_motion);
		result = prime * result + Float.floatToIntBits(minimum_orbit_intersection);
		result = prime * result + ((orbit_determination_date == null) ? 0 : orbit_determination_date.hashCode());
		result = prime * result + orbit_id;
		result = prime * result + orbit_uncertainty;
		result = prime * result + Float.floatToIntBits(orbital_period);
		result = prime * result + Float.floatToIntBits(perihelion_argument);
		result = prime * result + Float.floatToIntBits(perihelion_distance);
		result = prime * result + Float.floatToIntBits(perihelion_time);
		result = prime * result + Float.floatToIntBits(semi_major_axis);
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
		OrbitalData other = (OrbitalData) obj;
		if (Float.floatToIntBits(aphelion_distance) != Float.floatToIntBits(other.aphelion_distance))
			return false;
		if (Float.floatToIntBits(ascending_node_longitude) != Float.floatToIntBits(other.ascending_node_longitude))
			return false;
		if (Float.floatToIntBits(eccentricity) != Float.floatToIntBits(other.eccentricity))
			return false;
		if (Float.floatToIntBits(epoch_osculation) != Float.floatToIntBits(other.epoch_osculation))
			return false;
		if (equinox == null) {
			if (other.equinox != null)
				return false;
		} else if (!equinox.equals(other.equinox))
			return false;
		if (Float.floatToIntBits(inclination) != Float.floatToIntBits(other.inclination))
			return false;
		if (Float.floatToIntBits(jupiter_tisserand_invariant) != Float
				.floatToIntBits(other.jupiter_tisserand_invariant))
			return false;
		if (Float.floatToIntBits(mean_anomaly) != Float.floatToIntBits(other.mean_anomaly))
			return false;
		if (Float.floatToIntBits(mean_motion) != Float.floatToIntBits(other.mean_motion))
			return false;
		if (Float.floatToIntBits(minimum_orbit_intersection) != Float.floatToIntBits(other.minimum_orbit_intersection))
			return false;
		if (orbit_determination_date == null) {
			if (other.orbit_determination_date != null)
				return false;
		} else if (!orbit_determination_date.equals(other.orbit_determination_date))
			return false;
		if (orbit_id != other.orbit_id)
			return false;
		if (orbit_uncertainty != other.orbit_uncertainty)
			return false;
		if (Float.floatToIntBits(orbital_period) != Float.floatToIntBits(other.orbital_period))
			return false;
		if (Float.floatToIntBits(perihelion_argument) != Float.floatToIntBits(other.perihelion_argument))
			return false;
		if (Float.floatToIntBits(perihelion_distance) != Float.floatToIntBits(other.perihelion_distance))
			return false;
		if (Float.floatToIntBits(perihelion_time) != Float.floatToIntBits(other.perihelion_time))
			return false;
		if (Float.floatToIntBits(semi_major_axis) != Float.floatToIntBits(other.semi_major_axis))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrbitalData [orbit_id=" + orbit_id + ", orbit_determination_date=" + orbit_determination_date
				+ ", orbit_uncertainty=" + orbit_uncertainty + ", minimum_orbit_intersection="
				+ minimum_orbit_intersection + ", jupiter_tisserand_invariant=" + jupiter_tisserand_invariant
				+ ", epoch_osculation=" + epoch_osculation + ", eccentricity=" + eccentricity + ", semi_major_axis="
				+ semi_major_axis + ", inclination=" + inclination + ", ascending_node_longitude="
				+ ascending_node_longitude + ", orbital_period=" + orbital_period + ", perihelion_distance="
				+ perihelion_distance + ", perihelion_argument=" + perihelion_argument + ", aphelion_distance="
				+ aphelion_distance + ", perihelion_time=" + perihelion_time + ", mean_anomaly=" + mean_anomaly
				+ ", mean_motion=" + mean_motion + ", equinox=" + equinox + "]";
	}

	
	
}
