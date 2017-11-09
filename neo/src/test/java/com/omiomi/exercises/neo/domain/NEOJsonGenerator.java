package com.omiomi.exercises.neo.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.omiomi.exercises.neo.domain.CloseApproachData;
import com.omiomi.exercises.neo.domain.EstimatedDiameter;
import com.omiomi.exercises.neo.domain.FlatNEO;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.domain.OrbitalData;

/**
 * Class to generate test JSON data.
 * @author omi
 *
 */
public class NEOJsonGenerator {
	
	public static final int FULL_OBJ_NUM_ELEMENTS=14;
	
	public static final String ESTIMATED_DIAMETER_KM ="{\n" + 
			"          \"estimated_diameter_min\" : 0.0573792644,\n" + 
			"          \"estimated_diameter_max\" : 0.1283039358\n" + 
			"        }";
	
	public static EstimatedDiameter getEstimatedDiameter(float min, float max) {
		EstimatedDiameter ret = new EstimatedDiameter();
		ret.setMin(min);
		ret.setMax(max);
		return ret;
	}
	
	public static EstimatedDiameter getEstimatedDiameterSampleInKM() {
		return getEstimatedDiameter(0.0573792644f,0.1283039358f);
	}
	
	public static Map<String,EstimatedDiameter> getEstimatedDiameterSample() {
		Map<String,EstimatedDiameter> estDiameterMap= new LinkedHashMap<>();
		estDiameterMap.put("kilometers", getEstimatedDiameter(0.0573792644f,0.1283039358f));
		estDiameterMap.put("meters", getEstimatedDiameter(57.3792644396f, 128.3039357859f));
		estDiameterMap.put("miles", getEstimatedDiameter(0.0356538109f,0.0797243449f));
		estDiameterMap.put("feet", getEstimatedDiameter(188.2521859441f,420.9446846839f));
		return Collections.unmodifiableMap(estDiameterMap);
	}
	
	public static final String ESTIMATED_DIAMETER ="{\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0573792644,\n" + 
			"          \"estimated_diameter_max\" : 0.1283039358\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 57.3792644396,\n" + 
			"          \"estimated_diameter_max\" : 128.3039357859\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0356538109,\n" + 
			"          \"estimated_diameter_max\" : 0.0797243449\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 188.2521859441,\n" + 
			"          \"estimated_diameter_max\" : 420.9446846839\n" + 
			"        }\n";
	
	public static final CloseApproachData getCloseApproachSample() {
		CloseApproachData ret = new CloseApproachData();
		ret.setCloseApproachDate("2017-10-03");
		ret.setEpochDateCloseApproach(1507014000000l);
		Map<String,Float> relativeVelocity = new LinkedHashMap<>();
		relativeVelocity.put("kilometers_per_second", 8.8179639694f);
		relativeVelocity.put("kilometers_per_hour", 31744.670289722f);
		relativeVelocity.put("miles_per_hour", 19724.9036030775f);
		ret.setRelativeVelocity(relativeVelocity);
		
		Map<String,Float> missDistance = new LinkedHashMap<>();
		missDistance.put("astronomical", 0.0820326501f);
		missDistance.put("lunar", 31.9107017517f);
		missDistance.put("kilometers", 12271910.0f);
		missDistance.put("miles", 7625411.5f);
		ret.setMissDistance(missDistance);
		ret.setOrbitingBody("Earth");
		
		return ret;
	}
	
	public static final String CLOSE_APPROACH_DATA_JSON = "{\n" +
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"8.8179639694\",\n" + 
			"          \"kilometers_per_hour\" : \"31744.670289722\",\n" + 
			"          \"miles_per_hour\" : \"19724.9036030775\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.0820326501\",\n" + 
			"          \"lunar\" : \"31.9107017517\",\n" + 
			"          \"kilometers\" : \"12271910\",\n" + 
			"          \"miles\" : \"7625411.5\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n}";
	
	public static NearEarthObject getNEOSample() {
		NearEarthObject neo = new NearEarthObject();
		neo.setNeoReferenceId(3781732);
		neo.setName("(2017 SM12)");
		neo.setAbsoluteMagnitudeH(23.329f);
		neo.setMeasurements(getEstimatedDiameterSample());
		neo.setPotentiallyHazardousAsteroid(false);
		List<CloseApproachData> closeApproachData = new ArrayList<>();
		closeApproachData.add(getCloseApproachSample());
		neo.setCloseApproach(closeApproachData);
		return neo;
	}
	
	public static final String NEAR_EARTH_OBJECT_JSON = "{\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3781732?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3781732\",\n" + 
			"      \"name\" : \"(2017 SM12)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3781732\",\n" + 
			"      \"absolute_magnitude_h\" : 23.329,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0573792644,\n" + 
			"          \"estimated_diameter_max\" : 0.1283039358\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 57.3792644396,\n" + 
			"          \"estimated_diameter_max\" : 128.3039357859\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0356538109,\n" + 
			"          \"estimated_diameter_max\" : 0.0797243449\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 188.2521859441,\n" + 
			"          \"estimated_diameter_max\" : 420.9446846839\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"8.8179639694\",\n" + 
			"          \"kilometers_per_hour\" : \"31744.670289722\",\n" + 
			"          \"miles_per_hour\" : \"19724.9036030775\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.0820326501\",\n" + 
			"          \"lunar\" : \"31.9107017517\",\n" + 
			"          \"kilometers\" : \"12271910\",\n" + 
			"          \"miles\" : \"7625411.5\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }";
	
	
	
	public static final String FULL_OBJ_JSON ="{\n" +
			"    \"links\" : {\n" + 
			"    \"next\" : \"https://api.nasa.gov/neo/rest/v1/feed?start_date=2017-10-04&end_date=2017-10-04&detailed=false&api_key=DEMO_KEY\",\n" + 
			"    \"prev\" : \"https://api.nasa.gov/neo/rest/v1/feed?start_date=2017-10-02&end_date=2017-10-02&detailed=false&api_key=DEMO_KEY\",\n" + 
			"    \"self\" : \"https://api.nasa.gov/neo/rest/v1/feed?start_date=2017-10-03&end_date=2017-10-03&detailed=false&api_key=DEMO_KEY\"\n" + 
			"  },\n" + 
			"  \"element_count\" : 14,\n" + 
			"  \"near_earth_objects\" : {\n" + 
			"    \"2017-10-03\" : [ {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3781732?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3781732\",\n" + 
			"      \"name\" : \"(2017 SM12)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3781732\",\n" + 
			"      \"absolute_magnitude_h\" : 23.329,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0573792644,\n" + 
			"          \"estimated_diameter_max\" : 0.1283039358\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 57.3792644396,\n" + 
			"          \"estimated_diameter_max\" : 128.3039357859\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0356538109,\n" + 
			"          \"estimated_diameter_max\" : 0.0797243449\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 188.2521859441,\n" + 
			"          \"estimated_diameter_max\" : 420.9446846839\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"8.8179639694\",\n" + 
			"          \"kilometers_per_hour\" : \"31744.670289722\",\n" + 
			"          \"miles_per_hour\" : \"19724.9036030775\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.0820326501\",\n" + 
			"          \"lunar\" : \"31.9107017517\",\n" + 
			"          \"kilometers\" : \"12271910\",\n" + 
			"          \"miles\" : \"7625411.5\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/2004197?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"2004197\",\n" + 
			"      \"name\" : \"4197 Morpheus (1982 TA)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=2004197\",\n" + 
			"      \"absolute_magnitude_h\" : 14.6,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 3.1956188672,\n" + 
			"          \"estimated_diameter_max\" : 7.1456210173\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 3195.6188672131,\n" + 
			"          \"estimated_diameter_max\" : 7145.6210172693\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 1.9856648911,\n" + 
			"          \"estimated_diameter_max\" : 4.4400816771\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 10484.3142043074,\n" + 
			"          \"estimated_diameter_max\" : 23443.6392582979\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"37.78836284\",\n" + 
			"          \"kilometers_per_hour\" : \"136038.106224018\",\n" + 
			"          \"miles_per_hour\" : \"84528.7888368072\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.4736511843\",\n" + 
			"          \"lunar\" : \"184.2503051758\",\n" + 
			"          \"kilometers\" : \"70857208\",\n" + 
			"          \"miles\" : \"44028628\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3781028?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3781028\",\n" + 
			"      \"name\" : \"(2017 QP17)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3781028\",\n" + 
			"      \"absolute_magnitude_h\" : 19.57,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.3240074354,\n" + 
			"          \"estimated_diameter_max\" : 0.7245026508\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 324.0074353942,\n" + 
			"          \"estimated_diameter_max\" : 724.5026507569\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.2013288241,\n" + 
			"          \"estimated_diameter_max\" : 0.4501849366\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 1063.0165543388,\n" + 
			"          \"estimated_diameter_max\" : 2376.9772767092\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"17.3963031846\",\n" + 
			"          \"kilometers_per_hour\" : \"62626.6914644614\",\n" + 
			"          \"miles_per_hour\" : \"38913.7905935704\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.3933616992\",\n" + 
			"          \"lunar\" : \"153.0177001953\",\n" + 
			"          \"kilometers\" : \"58846072\",\n" + 
			"          \"miles\" : \"36565252\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3781722?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3781722\",\n" + 
			"      \"name\" : \"(2017 SV11)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3781722\",\n" + 
			"      \"absolute_magnitude_h\" : 24.015,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0418364645,\n" + 
			"          \"estimated_diameter_max\" : 0.0935491786\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 41.8364645375,\n" + 
			"          \"estimated_diameter_max\" : 93.5491786441\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0259959658,\n" + 
			"          \"estimated_diameter_max\" : 0.0581287467\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 137.2587463133,\n" + 
			"          \"estimated_diameter_max\" : 306.9198872628\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"6.3533791487\",\n" + 
			"          \"kilometers_per_hour\" : \"22872.1649352134\",\n" + 
			"          \"miles_per_hour\" : \"14211.8738176608\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.121103487\",\n" + 
			"          \"lunar\" : \"47.1092567444\",\n" + 
			"          \"kilometers\" : \"18116824\",\n" + 
			"          \"miles\" : \"11257273\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/2162385?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"2162385\",\n" + 
			"      \"name\" : \"162385 (2000 BM19)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=2162385\",\n" + 
			"      \"absolute_magnitude_h\" : 18.4,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.5553349116,\n" + 
			"          \"estimated_diameter_max\" : 1.2417666126\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 555.334911581,\n" + 
			"          \"estimated_diameter_max\" : 1241.766612574\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.3450690093,\n" + 
			"          \"estimated_diameter_max\" : 0.7715977618\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 1821.9649913114,\n" + 
			"          \"estimated_diameter_max\" : 4074.0375731972\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"15.2002611262\",\n" + 
			"          \"kilometers_per_hour\" : \"54720.9400544219\",\n" + 
			"          \"miles_per_hour\" : \"34001.4641132601\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.4745685014\",\n" + 
			"          \"lunar\" : \"184.6071472168\",\n" + 
			"          \"kilometers\" : \"70994440\",\n" + 
			"          \"miles\" : \"44113900\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3014792?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3014792\",\n" + 
			"      \"name\" : \"(1998 SB15)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3014792\",\n" + 
			"      \"absolute_magnitude_h\" : 20.9,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.1756123185,\n" + 
			"          \"estimated_diameter_max\" : 0.3926810818\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 175.6123184804,\n" + 
			"          \"estimated_diameter_max\" : 392.6810818086\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.1091204019,\n" + 
			"          \"estimated_diameter_max\" : 0.2440006365\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 576.1559189633,\n" + 
			"          \"estimated_diameter_max\" : 1288.3238004408\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"8.0361995881\",\n" + 
			"          \"kilometers_per_hour\" : \"28930.3185171093\",\n" + 
			"          \"miles_per_hour\" : \"17976.1748585894\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.165688671\",\n" + 
			"          \"lunar\" : \"64.4528884888\",\n" + 
			"          \"kilometers\" : \"24786672\",\n" + 
			"          \"miles\" : \"15401724\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3781891?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3781891\",\n" + 
			"      \"name\" : \"(2017 ST16)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3781891\",\n" + 
			"      \"absolute_magnitude_h\" : 24.284,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0369619586,\n" + 
			"          \"estimated_diameter_max\" : 0.0826494521\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 36.9619586299,\n" + 
			"          \"estimated_diameter_max\" : 82.6494520779\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0229670892,\n" + 
			"          \"estimated_diameter_max\" : 0.0513559727\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 121.2662723512,\n" + 
			"          \"estimated_diameter_max\" : 271.1596283553\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"7.8945226673\",\n" + 
			"          \"kilometers_per_hour\" : \"28420.2816023502\",\n" + 
			"          \"miles_per_hour\" : \"17659.257754527\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.1306448224\",\n" + 
			"          \"lunar\" : \"50.8208389282\",\n" + 
			"          \"kilometers\" : \"19544188\",\n" + 
			"          \"miles\" : \"12144196\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3739063?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3739063\",\n" + 
			"      \"name\" : \"(2015 YA10)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3739063\",\n" + 
			"      \"absolute_magnitude_h\" : 20.5,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.2111324448,\n" + 
			"          \"estimated_diameter_max\" : 0.4721064988\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 211.1324447897,\n" + 
			"          \"estimated_diameter_max\" : 472.1064988055\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.1311915784,\n" + 
			"          \"estimated_diameter_max\" : 0.2933532873\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 692.6917701639,\n" + 
			"          \"estimated_diameter_max\" : 1548.9058855411\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"21.701441338\",\n" + 
			"          \"kilometers_per_hour\" : \"78125.1888168205\",\n" + 
			"          \"miles_per_hour\" : \"48543.954128985\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.4555709905\",\n" + 
			"          \"lunar\" : \"177.2171173096\",\n" + 
			"          \"kilometers\" : \"68152448\",\n" + 
			"          \"miles\" : \"42347972\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3772696?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3772696\",\n" + 
			"      \"name\" : \"(2017 FJ90)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3772696\",\n" + 
			"      \"absolute_magnitude_h\" : 19.202,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.3838441946,\n" + 
			"          \"estimated_diameter_max\" : 0.858301712\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 383.8441946378,\n" + 
			"          \"estimated_diameter_max\" : 858.3017119788\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.2385096511,\n" + 
			"          \"estimated_diameter_max\" : 0.5333237931\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 1259.3313875355,\n" + 
			"          \"estimated_diameter_max\" : 2815.9505887285\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"21.0181194141\",\n" + 
			"          \"kilometers_per_hour\" : \"75665.2298909148\",\n" + 
			"          \"miles_per_hour\" : \"47015.4313174965\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.1563338137\",\n" + 
			"          \"lunar\" : \"60.8138542175\",\n" + 
			"          \"kilometers\" : \"23387206\",\n" + 
			"          \"miles\" : \"14532136\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3781027?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3781027\",\n" + 
			"      \"name\" : \"(2017 QS17)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3781027\",\n" + 
			"      \"absolute_magnitude_h\" : 19.726,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.3015469753,\n" + 
			"          \"estimated_diameter_max\" : 0.6742795352\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 301.5469753113,\n" + 
			"          \"estimated_diameter_max\" : 674.2795352056\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.1873725456,\n" + 
			"          \"estimated_diameter_max\" : 0.4189777491\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 989.3273784804,\n" + 
			"          \"estimated_diameter_max\" : 2212.2032702839\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"13.8631799504\",\n" + 
			"          \"kilometers_per_hour\" : \"49907.4478215725\",\n" + 
			"          \"miles_per_hour\" : \"31010.5472311321\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.2498398761\",\n" + 
			"          \"lunar\" : \"97.187713623\",\n" + 
			"          \"kilometers\" : \"37375512\",\n" + 
			"          \"miles\" : \"23224068\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3781899?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3781899\",\n" + 
			"      \"name\" : \"(2017 SS16)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3781899\",\n" + 
			"      \"absolute_magnitude_h\" : 26.374,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0141174083,\n" + 
			"          \"estimated_diameter_max\" : 0.0315674847\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 14.1174083377,\n" + 
			"          \"estimated_diameter_max\" : 31.5674847092\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0087721481,\n" + 
			"          \"estimated_diameter_max\" : 0.0196151195\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 46.3169579707,\n" + 
			"          \"estimated_diameter_max\" : 103.5678665334\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"16.2784562494\",\n" + 
			"          \"kilometers_per_hour\" : \"58602.442497984\",\n" + 
			"          \"miles_per_hour\" : \"36413.2787843723\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.0138338481\",\n" + 
			"          \"lunar\" : \"5.3813667297\",\n" + 
			"          \"kilometers\" : \"2069514.25\",\n" + 
			"          \"miles\" : \"1285936.5\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/2496837?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"2496837\",\n" + 
			"      \"name\" : \"496837 (1998 SB15)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=2496837\",\n" + 
			"      \"absolute_magnitude_h\" : 20.9,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.1756123185,\n" + 
			"          \"estimated_diameter_max\" : 0.3926810818\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 175.6123184804,\n" + 
			"          \"estimated_diameter_max\" : 392.6810818086\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.1091204019,\n" + 
			"          \"estimated_diameter_max\" : 0.2440006365\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 576.1559189633,\n" + 
			"          \"estimated_diameter_max\" : 1288.3238004408\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"8.0362030775\",\n" + 
			"          \"kilometers_per_hour\" : \"28930.3310791542\",\n" + 
			"          \"miles_per_hour\" : \"17976.1826641556\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.1656886695\",\n" + 
			"          \"lunar\" : \"64.4528884888\",\n" + 
			"          \"kilometers\" : \"24786672\",\n" + 
			"          \"miles\" : \"15401724\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3785691?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3785691\",\n" + 
			"      \"name\" : \"(2017 TE2)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3785691\",\n" + 
			"      \"absolute_magnitude_h\" : 23.22,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0603330078,\n" + 
			"          \"estimated_diameter_max\" : 0.1349087066\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 60.3330077631,\n" + 
			"          \"estimated_diameter_max\" : 134.9087066453\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0374891814,\n" + 
			"          \"estimated_diameter_max\" : 0.083828358\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 197.9429451894,\n" + 
			"          \"estimated_diameter_max\" : 442.61388111\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"14.6574982317\",\n" + 
			"          \"kilometers_per_hour\" : \"52766.9936339796\",\n" + 
			"          \"miles_per_hour\" : \"32787.3577944025\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.0333096919\",\n" + 
			"          \"lunar\" : \"12.9574699402\",\n" + 
			"          \"kilometers\" : \"4983059\",\n" + 
			"          \"miles\" : \"3096329.25\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    }, {\n" + 
			"      \"links\" : {\n" + 
			"        \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3785759?api_key=DEMO_KEY\"\n" + 
			"      },\n" + 
			"      \"neo_reference_id\" : \"3785759\",\n" + 
			"      \"name\" : \"(2017 TY3)\",\n" + 
			"      \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3785759\",\n" + 
			"      \"absolute_magnitude_h\" : 25.324,\n" + 
			"      \"estimated_diameter\" : {\n" + 
			"        \"kilometers\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0228957554,\n" + 
			"          \"estimated_diameter_max\" : 0.0511964654\n" + 
			"        },\n" + 
			"        \"meters\" : {\n" + 
			"          \"estimated_diameter_min\" : 22.8957553906,\n" + 
			"          \"estimated_diameter_max\" : 51.1964654497\n" + 
			"        },\n" + 
			"        \"miles\" : {\n" + 
			"          \"estimated_diameter_min\" : 0.0142267584,\n" + 
			"          \"estimated_diameter_max\" : 0.0318119989\n" + 
			"        },\n" + 
			"        \"feet\" : {\n" + 
			"          \"estimated_diameter_min\" : 75.1173101158,\n" + 
			"          \"estimated_diameter_max\" : 167.9674117059\n" + 
			"        }\n" + 
			"      },\n" + 
			"      \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"      \"close_approach_data\" : [ {\n" + 
			"        \"close_approach_date\" : \"2017-10-03\",\n" + 
			"        \"epoch_date_close_approach\" : 1507014000000,\n" + 
			"        \"relative_velocity\" : {\n" + 
			"          \"kilometers_per_second\" : \"16.6909561365\",\n" + 
			"          \"kilometers_per_hour\" : \"60087.4420913024\",\n" + 
			"          \"miles_per_hour\" : \"37335.9997816762\"\n" + 
			"        },\n" + 
			"        \"miss_distance\" : {\n" + 
			"          \"astronomical\" : \"0.0109680638\",\n" + 
			"          \"lunar\" : \"4.266576767\",\n" + 
			"          \"kilometers\" : \"1640799\",\n" + 
			"          \"miles\" : \"1019545.25\"\n" + 
			"        },\n" + 
			"        \"orbiting_body\" : \"Earth\"\n" + 
			"      } ]\n" + 
			"    } ]\n" + 
			"  }\n" + 
			"}";
	
	public static final String ORBITAL_DATA_JSON ="{\n" + 
			"    \"orbit_id\" : \"18\",\n" + 
			"    \"orbit_determination_date\" : \"2017-04-06 08:57:01\",\n" + 
			"    \"orbit_uncertainty\" : \"1\",\n" + 
			"    \"minimum_orbit_intersection\" : \".0160464\",\n" + 
			"    \"jupiter_tisserand_invariant\" : \"8.147\",\n" + 
			"    \"epoch_osculation\" : \"2458000.5\",\n" + 
			"    \"eccentricity\" : \".6757028909734134\",\n" + 
			"    \"semi_major_axis\" : \".6822865948551869\",\n" + 
			"    \"inclination\" : \"12.5650948795334\",\n" + 
			"    \"ascending_node_longitude\" : \"306.5727058625229\",\n" + 
			"    \"orbital_period\" : \"205.8489636098803\",\n" + 
			"    \"perihelion_distance\" : \".2212635702391311\",\n" + 
			"    \"perihelion_argument\" : \"195.6160593779997\",\n" + 
			"    \"aphelion_distance\" : \"1.143309619471243\",\n" + 
			"    \"perihelion_time\" : \"2458008.590355668313\",\n" + 
			"    \"mean_anomaly\" : \"345.8511406153474\",\n" + 
			"    \"mean_motion\" : \"1.748855052203532\",\n" + 
			"    \"equinox\" : \"J2000\"\n" + 
			"  }";

	public static OrbitalData getOribalDataSample() {
		OrbitalData ob = new OrbitalData();
		ob.setOrbit_id(18);
		ob.setOrbit_determination_date("2017-04-06 08:57:01");
		ob.setOrbit_uncertainty(1);
		ob.setMinimum_orbit_intersection(.0160464f);
		ob.setJupiter_tisserand_invariant(8.147f);
		ob.setEpoch_osculation(2458000.5f);
		ob.setEccentricity(.6757028909734134f);
		ob.setSemi_major_axis(.6822865948551869f);
		ob.setInclination(12.5650948795334f);
		ob.setAscending_node_longitude(306.5727058625229f);
		ob.setOrbital_period(205.8489636098803f);
		ob.setPerihelion_distance(.2212635702391311f);
		ob.setPerihelion_argument(195.6160593779997f);
		ob.setAphelion_distance(1.143309619471243f);
		ob.setPerihelion_time(2458008.590355668313f);
		ob.setMean_anomaly(345.8511406153474f);
		ob.setMean_motion(1.748855052203532f);
		ob.setEquinox("J2000");
		return ob;
	}
	
	public static FlatNEO getFlatNEOSample() {
		NearEarthObject neo = getNEOSample();
		FlatNEO flatNeo = new FlatNEO();
		flatNeo.setSelfLink(String.format("https://api.nasa.gov/neo/rest/v1/neo/%s?api_key=DEMO_KEY",neo.getNeoReferenceId()))
			.setReferenceId(neo.getNeoReferenceId())
			.setName(neo.getName())
			.setAbsoluteMagnitude(neo.getAbsoluteMagnitudeH())
			.setEstimatedDiameterMax(neo.getMeasurements().get(CloseApproachData.KM).getMax())
			.setEstimatedDiameterMin(neo.getMeasurements().get(CloseApproachData.KM).getMin())
			.setPotentiallyHazardousAsteroid(neo.isPotentiallyHazardousAsteroid())
			.setRelativeVelocity(neo.getCloseApproach().get(0).getRelativeVelocity().get(CloseApproachData.KM_PER_SEC))
			.setMissDistance(neo.getCloseApproach().get(0).getMissDistance().get(CloseApproachData.KM))
			.setOrbitingBody(neo.getCloseApproach().get(0).getOrbitingBody());
		return flatNeo;
	}
	
	public static final String DETAILED_NEO_JSON = "{\n" + 
			"  \"links\" : {\n" + 
			"    \"self\" : \"https://api.nasa.gov/neo/rest/v1/neo/3650046?api_key=DEMO_KEY\"\n" + 
			"  },\n" + 
			"  \"neo_reference_id\" : \"3650046\",\n" + 
			"  \"name\" : \"(2013 TN69)\",\n" + 
			"  \"nasa_jpl_url\" : \"http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=3650046\",\n" + 
			"  \"absolute_magnitude_h\" : 23.3,\n" + 
			"  \"estimated_diameter\" : {\n" + 
			"    \"kilometers\" : {\n" + 
			"      \"estimated_diameter_min\" : 0.058150704,\n" + 
			"      \"estimated_diameter_max\" : 0.130028927\n" + 
			"    },\n" + 
			"    \"meters\" : {\n" + 
			"      \"estimated_diameter_min\" : 58.1507039646,\n" + 
			"      \"estimated_diameter_max\" : 130.0289270043\n" + 
			"    },\n" + 
			"    \"miles\" : {\n" + 
			"      \"estimated_diameter_min\" : 0.0361331611,\n" + 
			"      \"estimated_diameter_max\" : 0.0807962044\n" + 
			"    },\n" + 
			"    \"feet\" : {\n" + 
			"      \"estimated_diameter_min\" : 190.7831555951,\n" + 
			"      \"estimated_diameter_max\" : 426.6041048727\n" + 
			"    }\n" + 
			"  },\n" + 
			"  \"is_potentially_hazardous_asteroid\" : false,\n" + 
			"  \"close_approach_data\" : [ {\n" + 
			"    \"close_approach_date\" : \"2009-12-28\",\n" + 
			"    \"epoch_date_close_approach\" : 1261987200000,\n" + 
			"    \"relative_velocity\" : {\n" + 
			"      \"kilometers_per_second\" : \"15.6760210868\",\n" + 
			"      \"kilometers_per_hour\" : \"56433.6759124307\",\n" + 
			"      \"miles_per_hour\" : \"35065.6915690323\"\n" + 
			"    },\n" + 
			"    \"miss_distance\" : {\n" + 
			"      \"astronomical\" : \"0.3187902608\",\n" + 
			"      \"lunar\" : \"124.0094070435\",\n" + 
			"      \"kilometers\" : \"47690344\",\n" + 
			"      \"miles\" : \"29633406\"\n" + 
			"    },\n" + 
			"    \"orbiting_body\" : \"Earth\"\n" + 
			"  }, {\n" + 
			"    \"close_approach_date\" : \"2013-11-09\",\n" + 
			"    \"epoch_date_close_approach\" : 1383984000000,\n" + 
			"    \"relative_velocity\" : {\n" + 
			"      \"kilometers_per_second\" : \"7.5247841508\",\n" + 
			"      \"kilometers_per_hour\" : \"27089.2229428766\",\n" + 
			"      \"miles_per_hour\" : \"16832.1896669224\"\n" + 
			"    },\n" + 
			"    \"miss_distance\" : {\n" + 
			"      \"astronomical\" : \"0.0833845227\",\n" + 
			"      \"lunar\" : \"32.436580658\",\n" + 
			"      \"kilometers\" : \"12474147\",\n" + 
			"      \"miles\" : \"7751075.5\"\n" + 
			"    },\n" + 
			"    \"orbiting_body\" : \"Earth\"\n" + 
			"  }, {\n" + 
			"    \"close_approach_date\" : \"2017-10-01\",\n" + 
			"    \"epoch_date_close_approach\" : 1506841200000,\n" + 
			"    \"relative_velocity\" : {\n" + 
			"      \"kilometers_per_second\" : \"16.9489205389\",\n" + 
			"      \"kilometers_per_hour\" : \"61016.1139399712\",\n" + 
			"      \"miles_per_hour\" : \"37913.0403534227\"\n" + 
			"    },\n" + 
			"    \"miss_distance\" : {\n" + 
			"      \"astronomical\" : \"0.3192859737\",\n" + 
			"      \"lunar\" : \"124.2022399902\",\n" + 
			"      \"kilometers\" : \"47764500\",\n" + 
			"      \"miles\" : \"29679484\"\n" + 
			"    },\n" + 
			"    \"orbiting_body\" : \"Earth\"\n" + 
			"  } ],\n" + 
			"  \"orbital_data\" : {\n" + 
			"    \"orbit_id\" : \"17\",\n" + 
			"    \"orbit_determination_date\" : \"2017-04-06 08:44:14\",\n" + 
			"    \"orbit_uncertainty\" : \"6\",\n" + 
			"    \"minimum_orbit_intersection\" : \".0772597\",\n" + 
			"    \"jupiter_tisserand_invariant\" : \"3.197\",\n" + 
			"    \"epoch_osculation\" : \"2458000.5\",\n" + 
			"    \"eccentricity\" : \".5882743297464178\",\n" + 
			"    \"semi_major_axis\" : \"2.496973777688111\",\n" + 
			"    \"inclination\" : \"6.553903814343643\",\n" + 
			"    \"ascending_node_longitude\" : \"190.1839210047635\",\n" + 
			"    \"orbital_period\" : \"1441.183891423705\",\n" + 
			"    \"perihelion_distance\" : \"1.028068202224257\",\n" + 
			"    \"perihelion_argument\" : \"225.9035323744481\",\n" + 
			"    \"aphelion_distance\" : \"3.965879353151966\",\n" + 
			"    \"perihelion_time\" : \"2458054.812541444657\",\n" + 
			"    \"mean_anomaly\" : \"346.4330186894047\",\n" + 
			"    \"mean_motion\" : \".2497946321370316\",\n" + 
			"    \"equinox\" : \"J2000\"\n" + 
			"  }\n" + 
			"}";
	
}
