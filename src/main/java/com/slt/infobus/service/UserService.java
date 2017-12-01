/**
 * 
 */
package com.slt.infobus.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.slt.infobus.entity.User;

/**
 * @author saikrishna
 *
 */
public interface UserService {

	JSONObject doLogin(JSONObject json);

	JSONObject userRegister(JSONObject json);
	
	List<User> getUsers();
	
	User registerUser(User user);	
	
	void delete(User usr);
	
	User getUser(int id);
}
