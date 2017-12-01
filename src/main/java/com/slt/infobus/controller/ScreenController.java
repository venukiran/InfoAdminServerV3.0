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

import com.slt.infobus.entity.IBScreenDtl;
import com.slt.infobus.repository.ScreenDAO;

@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class ScreenController {
	private Logger log = Logger.getLogger(ScreenController.class);

	@Autowired
	private ScreenDAO screenRepo;
	
	
	@RequestMapping(value="/screens", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBScreenDtl> getAllScreens() {
        List<IBScreenDtl> allScreens = null;
        try{
        	allScreens=screenRepo.findAll();
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        return  allScreens;
	}
	
	@RequestMapping(value="/screens", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addScreen(@RequestBody IBScreenDtl screen ) {
		log.info("add screen id::"+screen);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
        IBScreenDtl allScreens = screenRepo.save(screen);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}
	
	@RequestMapping(value="/screens", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updateScreen(@RequestBody IBScreenDtl screen ) {
		log.info("update screen id::"+screen);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
			IBScreenDtl allScreens = screenRepo.save(screen);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
    }

	@RequestMapping(value="/screens/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteScreen(@PathVariable("id") int id) {
		log.info("delete screen id::"+id);
		IBScreenDtl loc = screenRepo.getScreen(id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";;
		try{
			screenRepo.delete(loc);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;        
	}
	
	@RequestMapping(value="/screens/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBScreenDtl getScreenById(@PathVariable int id) {
		log.info("get screen id::"+id);
        IBScreenDtl allScreens = screenRepo.getScreen(id);
        return  allScreens;
	}
	
}
