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

import com.slt.infobus.entity.IBScreenPositionDtl;
import com.slt.infobus.repository.ScreenPositionDAO;

@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class ScreenPositionController {
	
	private Logger log = Logger.getLogger(ScreenPositionController.class);
	
	@Autowired
	private ScreenPositionDAO screenRepo;
	
	
	@RequestMapping(value="/screenPosition", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBScreenPositionDtl> getAllScreens() {
        List<IBScreenPositionDtl> allScreens = screenRepo.findAll();
        return  allScreens;
	}
	
	@RequestMapping(value="/screenPosition", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public IBScreenPositionDtl addScreen(@RequestBody IBScreenPositionDtl screen ) {
		log.info("add screen id::"+screen);
        IBScreenPositionDtl allScreens = screenRepo.save(screen);
        return  allScreens;
	}
	
	@RequestMapping(value="/screenPosition", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public IBScreenPositionDtl updateScreen(@RequestBody IBScreenPositionDtl screen ) {
		log.info("update screen id::"+screen);
        IBScreenPositionDtl allScreens = screenRepo.save(screen);
        return  allScreens;
	}

	@RequestMapping(value="/screenPosition/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteScreen(@PathVariable("id") int id) {
		log.info("delete screen id::"+id);
		IBScreenPositionDtl loc = screenRepo.getScreen(id);
        screenRepo.delete(loc);
        return "{\"statusCode\":200, \"Msg\":\"DELETED\"}"; 
	}
	
	@RequestMapping(value="/screenPosition/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBScreenPositionDtl getScreenById(@PathVariable int id) {
		log.info("get screen id::"+id);
        IBScreenPositionDtl allScreens = screenRepo.getScreen(id);
        return  allScreens;
	}
	
}
