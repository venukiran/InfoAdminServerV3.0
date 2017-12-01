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

import com.slt.infobus.entity.IBSponserDtl;
import com.slt.infobus.repository.SponserDAO;

@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class SponserController {
	private Logger log = Logger.getLogger(SponserController.class);

	@Autowired
	private SponserDAO sponserRepo;
	
	
	@RequestMapping(value="/sponsers", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBSponserDtl> getAllSponsers() {
        List<IBSponserDtl> allSponsers = sponserRepo.findAll();
        return  allSponsers;
	}
	
	@RequestMapping(value="/sponsers", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addSponser(@RequestBody IBSponserDtl sponser ) {
		log.info("add sponser id::"+sponser);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
		    IBSponserDtl allSponsers = sponserRepo.save(sponser);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}
	
	@RequestMapping(value="/sponsers", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updateSponser(@RequestBody IBSponserDtl sponser ) {
		log.info("update sponser id::"+sponser);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
		    IBSponserDtl allSponsers = sponserRepo.save(sponser);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}

	@RequestMapping(value="/sponsers/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteSponser(@PathVariable("id") int id) {
		log.info("delete sponser id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";;
		try{
			IBSponserDtl loc = sponserRepo.getSponser(id);
			sponserRepo.delete(loc);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg; 
	}
	
	@RequestMapping(value="/sponsers/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBSponserDtl getSponserById(@PathVariable int id) {
		log.info("get sponser id::"+id);
        IBSponserDtl allSponsers = sponserRepo.getSponser(id);
        return  allSponsers;
	}
	
}
