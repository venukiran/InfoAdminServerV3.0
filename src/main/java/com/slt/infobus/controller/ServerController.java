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

import com.slt.infobus.entity.IBServerDtl;
import com.slt.infobus.repository.ServerDAO;


@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class ServerController {
	private Logger log = Logger.getLogger(ServerController.class);

	@Autowired
	private ServerDAO serverRepo;
	
	
	@RequestMapping(value="/servers", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBServerDtl> getAllServers() {
        List<IBServerDtl> allServers = serverRepo.findAll();
        return  allServers;
	}
	
	@RequestMapping(value="/servers", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addServer(@RequestBody IBServerDtl server ) {
		log.info("add server id::"+server);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
			IBServerDtl allServers = serverRepo.save(server);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}
	
	@RequestMapping(value="/servers", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updateServer(@RequestBody IBServerDtl server ) {
		log.info("update server id::"+server);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
			IBServerDtl allServers = serverRepo.save(server);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}

	@RequestMapping(value="/servers/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteServer(@PathVariable("id") int id) {
		log.info("delete Server id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";
		try{
			IBServerDtl loc = serverRepo.getServer(id);
			serverRepo.delete(loc);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}
	
	@RequestMapping(value="/servers/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBServerDtl getServerById(@PathVariable int id) {
		log.info("get Server id::"+id);
        IBServerDtl allServers = serverRepo.getServer(id);
        return  allServers;
	}
	
}
