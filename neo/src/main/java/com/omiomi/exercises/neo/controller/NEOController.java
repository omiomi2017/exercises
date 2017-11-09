package com.omiomi.exercises.neo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omiomi.exercises.neo.domain.FlatNEO;
import com.omiomi.exercises.neo.domain.NearEarthObject;
import com.omiomi.exercises.neo.service.NEOService;

@RestController
public class NEOController {
	@Autowired NEOService neoService;
	
	/**
	 * Return JSON representation of biggest NearEarthOBject
	 * @param datefrom from date
	 * @param dateto to date
	 * @return JSON for biggest NearEarthObject
	 */
	@RequestMapping("/neodata/getbiggest/{datefrom}/{dateto}")
	public List<FlatNEO> getBiggest(@PathVariable("datefrom") String datefrom, 
			@PathVariable("dateto") String dateto) {
		List<FlatNEO> ret = neoService.getBiggestNearEarthObjects(datefrom, dateto);
		return ret;
	}
	
	/**
	 * Return JSON representation of closest NearEarthOBject
	 * @param datefrom from date
	 * @param dateto to date
	 * @return JSON for closest NearEarthObject
	 */
	@RequestMapping("/neodata/getclosest/{datefrom}/{dateto}")
	public List<FlatNEO> getClosest(@PathVariable("datefrom") String datefrom, 
			@PathVariable("dateto") String dateto) {
		List<FlatNEO> ret = neoService.getClosestNearEarthObjects(datefrom, dateto);
		return ret;
	}
	
	@RequestMapping("/neodata/detailedneo/{neoid}")
	public NearEarthObject getDetailedNEO(@PathVariable("neoid") int neoid){
		return neoService.getDetailedNEO(neoid);
	}
}
