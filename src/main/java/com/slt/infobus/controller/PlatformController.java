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

import com.slt.infobus.entity.IBPlatformDtl;
import com.slt.infobus.repository.PlatformDAO;

@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class PlatformController {

	private Logger log = Logger.getLogger(PlatformController.class);
	@Autowired
	private PlatformDAO platformRepo;
	
	
	@RequestMapping(value="/platforms", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBPlatformDtl> getAllPlatforms() {
        List<IBPlatformDtl> allPlatforms = platformRepo.findAll();
        return  allPlatforms;
	}
	
	@RequestMapping(value="/platforms", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addPlatforms(@RequestBody IBPlatformDtl platform ) {
		log.info("add platform id::"+platform);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
			IBPlatformDtl allPlatforms = platformRepo.save(platform);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}
	
	@RequestMapping(value="/platforms", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updatePlatforms(@RequestBody IBPlatformDtl platform ) {
		log.info("update platform id::"+platform);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
			IBPlatformDtl allPlatforms = platformRepo.save(platform);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}

	@RequestMapping(value="/platforms/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deletePlatforms(@PathVariable("id") int id) {
		log.info("delete platform id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";
		try{
			IBPlatformDtl loc = platformRepo.getPlatform(id);
			platformRepo.delete(loc);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}
	
	@RequestMapping(value="/platforms/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBPlatformDtl getPlatformsById(@PathVariable int id) {
		log.info("get platform id::"+id);
        IBPlatformDtl allPlatforms = platformRepo.getPlatform(id);
        return  allPlatforms;
	}
	
}
