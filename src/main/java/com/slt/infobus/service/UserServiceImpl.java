/**
 * 
 */
package com.slt.infobus.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slt.infobus.entity.User;
import com.slt.infobus.repository.UserDao;
import com.slt.infobus.util.CommonMethods;
import com.slt.infobus.util.GenerateUUID;
import com.slt.infobus.util.SHAEncryprtion;
import com.slt.infobus.util.StatusConstants;


/**
 * @author saikrishna
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Autowired
	private StatusConstants statusConstants;
	
	private CommonMethods cm;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JSONObject doLogin(JSONObject json) {
		JSONObject response = new JSONObject();
		try {
			if (json != null) {
				if (json.get("userName") != null && json.get("userName") != ""
						&& json.get("password") != null
						&& json.get("password") != "") {
					String userName = (String) json.get("userName");
					String password = (String) json.get("password");
					//String salt = SHAEncryprtion.getSalt();
					//String securePassword = SHAEncryprtion.get_SHA_1_SecurePassword(password, salt);
					User admin = userDao.checkAdminCredentials(userName,password);
					if (admin == null) {
						response.put(statusConstants.STATUS_CODE, statusConstants.STATUS_INVALID);
						response.put(statusConstants.STATUS_MESSAGE, statusConstants.SF_1027);
					} else {
						String accessToken = GenerateUUID.generate();
						if (accessToken != null) {
							admin.setAccessToken(accessToken);
							userDao.updateAdmin(admin);
						}
						response.put("userId", admin.getUserId());
						response.put("accessToken", accessToken);
						response.put("name", admin.getFirstName());					
						response.put(statusConstants.STATUS_CODE, statusConstants.STATUS_SUCCESS);
					}

				} else {
					response.put(statusConstants.STATUS_CODE, statusConstants.STATUS_INVALID);
					response.put(statusConstants.STATUS_MESSAGE, statusConstants.SF_1003);
				}
			} else {
				response.put(statusConstants.STATUS_CODE, statusConstants.STATUS_INVALID);
				response.put(statusConstants.STATUS_MESSAGE, statusConstants.SF_1002);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put(statusConstants.STATUS_CODE, statusConstants.STATUS_FAILURE);
			response.put(statusConstants.STATUS_MESSAGE, statusConstants.SF_1006);
		}

		return response;
	}

	@Override
	@Transactional
	public JSONObject userRegister(JSONObject json) {
		JSONObject response = new JSONObject();
		try {
			if (json != null) {

				if (json.get("firstName") != null
						&& json.get("firstName") != ""
						&& json.get("loginId") != null
						&& json.get("loginId") != ""
						// && json.get("dob")!=null&& json.get("dob")!=""
						//&& json.get("phone") != null
						//&& json.get("phone") != ""
						&& json.get("password") != null
						&& json.get("password") != "") {
							try {
								User user = new User();	
								user.setFirstName(json.get("firstName").toString());
								user.setFirstName(json.get("lastName").toString());
								user.setLoginId(json.get("loginId").toString());
								String passwordToHash = json.get("password").toString();
								String salt = SHAEncryprtion.getSalt();
								String securePassword = SHAEncryprtion.get_SHA_1_SecurePassword(passwordToHash, salt);

								user.setPassword(securePassword);
								user.setEmail(json.get("email").toString());
								user.setPhone(json.get("phone").toString());
								//user.setCreateDateTime(new Date());
								String accessToken = GenerateUUID.generate();
								if (accessToken != null) {
									user.setAccessToken(accessToken);
								}

								user = userDao.save(user);
								
								response.put("userId", user.getUserId());
								response.put("accessToken", accessToken);
								response.put(statusConstants.STATUS_CODE,statusConstants.STATUS_SUCCESS);
								response.put(statusConstants.STATUS_MESSAGE,statusConstants.SC_1010);

							} catch (Exception e) {
								e.printStackTrace();
								response.put(statusConstants.STATUS_CODE, statusConstants.STATUS_FAILURE);
								response.put(statusConstants.STATUS_MESSAGE, statusConstants.SF_1006);
							}
						} else {
							response.put(statusConstants.STATUS_CODE, statusConstants.STATUS_INVALID);
							response.put(statusConstants.STATUS_MESSAGE, statusConstants.SF_1004);
						}
					
			}else {
				response.put(statusConstants.STATUS_CODE,statusConstants.STATUS_INVALID);
				response.put(statusConstants.STATUS_MESSAGE,statusConstants.SF_1002);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put(statusConstants.STATUS_CODE,	statusConstants.STATUS_FAILURE);
			response.put(statusConstants.STATUS_MESSAGE, statusConstants.SF_1006);
		}

		return response;
	}

	public List<User> getUsers(){
		return userDao.findAll();
	}
	
	public User registerUser(User user){
		return userDao.save(user);
	}
		
	public User getUser(int id){
		return userDao.getUserById(id);
	}
	
	public void delete(User user){
		 userDao.delete(user);
	}
}
