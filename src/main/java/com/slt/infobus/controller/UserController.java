/**
 * 
 */
package com.slt.infobus.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.slt.infobus.entity.IBLocationDtl;
import com.slt.infobus.entity.User;
import com.slt.infobus.service.UserService;

import io.swagger.annotations.ApiParam;



/**
 * @author saikrishna
 *
 */
@CrossOrigin
//(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class UserController {
	private Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST,headers = "content-type=application/json" )
	@ResponseStatus(HttpStatus.OK)
	public JSONObject doLogin(@ApiParam(value = "json object",
			defaultValue="{\"email\":\"1\",\"password\":\"1\"}"
			, required = true, name = "json") @RequestBody JSONObject json){
		log.info("admin login json"+json);
		JSONObject response = new JSONObject();
		response= userService.doLogin(json);
		return response;
	}
	
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST, headers = "content-type=application/json")
	@ResponseStatus(HttpStatus.OK)
	public JSONObject userRegister(//@ApiParam(value = "json object",
			//defaultValue="{\"phone\":\"1\",\"email\":\"1\",\"firstName\":\"1\",\"password\":\"1\"}"
			//, required = true, name = "json") 
	@RequestBody JSONObject json) {
		log.info("in userRegister controller"+json);
		JSONObject response = new JSONObject();
		response= userService.userRegister(json);
		log.info("in userRegister response"+response);
		return response;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<User> getAllUsers() {
        List<User> allUsers = userService.getUsers();
        return  allUsers;
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String  registerNewUser(@RequestBody User user) {
		log.info("add User ::"+user.toString());
        String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
			userService.registerUser(user);
		}catch(Exception ex){
        	ex.printStackTrace();
        	msg = "{\"statusCode\":405, \"Msg\":\"ERROR\"}";
        }
	    return msg;
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteUser(@PathVariable("id") int id) {
		log.info("delete user id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";
		try{
			User usr = userService.getUser(id);		
			userService.delete(usr);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":405, \"Msg\":\"ERROR\"}";
		}
        return msg; 
	}

}
