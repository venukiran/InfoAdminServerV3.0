package com.slt.infobus.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slt.infobus.entity.IBLocationDtl;
import com.slt.infobus.repository.LocationDAO;


@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class LocationController {

	private Logger log = Logger.getLogger(LocationController.class);
	
	@Autowired
	private LocationDAO locationRepo;
	


	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String getMsg() {
        return "Hello World Rest services"; 
	}
	
	
	@RequestMapping(value="/locations", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBLocationDtl> getAllLocations() {
        List<IBLocationDtl> allLocs = locationRepo.findAll();
        return  allLocs;
	}
	
	@RequestMapping(value="/locations", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addLocation(@RequestBody IBLocationDtl location ) {
		System.out.println("add location ::"+location.toString());
		IBLocationDtl allLocs = null;
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
			allLocs = locationRepo.save(location);
		}catch(Exception ex){
        	ex.printStackTrace();
        	msg = "{\"statusCode\":405, \"Msg\":\"ERROR\"}";
        }
        return msg;
	}
	
	@RequestMapping(value="/locations", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updateLocation(@RequestBody IBLocationDtl location ) {
		System.out.println("add location ::"+location.toString());
		IBLocationDtl allLocs = null;
		String msg = "{\"statusCode\":200, \"Msg\":\"UPDATED\"}";
		try{
			allLocs = locationRepo.save(location);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":200, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}

	@RequestMapping(value="/locations/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteLocation(@PathVariable("id") int id) {
		System.out.println("delete location id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";
		try{
			IBLocationDtl loc = locationRepo.getLocation(id);		
	        locationRepo.delete(loc);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":405, \"Msg\":\"ERROR\"}";
		}
        return msg; 
	}
	
	@RequestMapping(value="/locations/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBLocationDtl getLocationById(@PathVariable int id) {
		System.out.println("get location id::"+id);
        IBLocationDtl allLocs = locationRepo.getLocation(id);
        return  allLocs;
	}
	
}
