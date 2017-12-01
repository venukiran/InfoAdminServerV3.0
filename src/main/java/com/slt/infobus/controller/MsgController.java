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

import com.slt.infobus.entity.IBMsgContentDtl;
import com.slt.infobus.repository.MsgDAO;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
@Api(value="MsgController", description="INFOBus MsgController")
public class MsgController {
	private Logger log = Logger.getLogger(MsgController.class);

	@Autowired
	private MsgDAO msgRepo;
	
	
	@RequestMapping(value="/msgcontent", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBMsgContentDtl> getAllMsgContents() {
        List<IBMsgContentDtl> allMsgs = msgRepo.findAll();
        return  allMsgs;
	}
	
	@RequestMapping(value="/msgcontent", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addMsgContent(@RequestBody IBMsgContentDtl msgcontent ) {
		log.info("add msgcontent id::"+msgcontent);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
		    IBMsgContentDtl allMsgs = msgRepo.save(msgcontent);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}
	
	@RequestMapping(value="/msgcontent", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updateMsgContent(@RequestBody IBMsgContentDtl msgcontent ) {
		log.info("update msgcontent id::"+msgcontent);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
		    IBMsgContentDtl allMsgs = msgRepo.save(msgcontent);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}

	@RequestMapping(value="/msgcontent/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteMsgContent(@PathVariable("id") int id) {
		log.info("delete msgcontent id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";
		try{
			IBMsgContentDtl loc = msgRepo.getMsgContent(id);			
			msgRepo.delete(loc);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}
	
	@RequestMapping(value="/msgcontent/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBMsgContentDtl getMsgContentById(@PathVariable int id) {
		log.info("get msgcontent id::"+id);
        IBMsgContentDtl allMsgs = msgRepo.getMsgContent(id);
        return  allMsgs;
	}
	
}
