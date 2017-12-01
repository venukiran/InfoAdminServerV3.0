/**
 * 
 */
package com.slt.infobus.util;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author saikrishna
 *
 */
@Component
public class StatusConstants implements InitializingBean {

	
		//Response Fields
		public static final String STATUS_CODE = "statusCode";
		public static final String STATUS_MESSAGE = "statusMessage";
		
		//Status Codes
		public static final String STATUS_SUCCESS = "200";  // if success 
		public static final String STATUS_FAILURE = "501";  // if some exception occurs
		public static final String STATUS_MISSING = "405";  // if some mandatoryfields missing in your request
		public static final String STATUS_INVALID = "404";  // for invalid requests
		public static final String STATUS_DUPLICATE_CODE = "201";  // for invalid requests
		public static final String STATUS_HAS_ASOCIATION= "204";  // if has asociation 
		public static final String STATUS_FORBIDDEN= "403";  // forbidden 
		public static final String STATUS_INVALID_SESSION= "401";  // invalid session 
		
		
		/**
		 * Failer messages starting from 1001
		 */
		public static final String SF_1001 = "OTP generation Failed, Please try after some time";
		public static final String SF_1002 = "Invalid Json Request";
		public static final String SF_1003 = "Missing Mandatory Fields";
		public static final String SF_1004 = "This mobile number registered with another email, please provide a new Mobile Number";
		public static final String SF_1005 = "This email registered with another mobile number, please provide a new email";
		public static final String SF_1006 = "Server exception, Please try after some time";

		public static final String SF_1027 = "Invalid credentials!";
		public static final String SC_1010 = "User Successfully created";
	

		@Override
		public void afterPropertiesSet() throws Exception {
			// TODO Auto-generated method stub
			
		}
	
		
		
	
		
		}
