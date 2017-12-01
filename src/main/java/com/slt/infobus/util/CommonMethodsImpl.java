/**
 * 
 */
package com.slt.infobus.util;

import java.awt.Image;


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;

import javax.imageio.ImageIO;import javax.persistence.ManyToOne;
import javax.transaction.Transactional;


import org.hibernate.ScrollableResults;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.slt.infobus.entity.User;
import com.slt.infobus.repository.UserDao;

import ch.qos.logback.core.status.StatusManager;



/**
 * @author saikrishna
 *
 */
@Component
public class CommonMethodsImpl implements CommonMethods {
	
	public static final Logger log = LoggerFactory.getLogger(CommonMethodsImpl.class);

	private static final Random generator = new Random();
	
	@Autowired
	private UserDao userDao;
	
	
	
	@Autowired
	private StatusConstants statusConstants;



	@Override
	public boolean EmailValidation(String email) {
		User user = userDao.getUserByMobile(email);

		if (user != null) {
			return true;
		}
		else
		return false;
	}



	@Override
	public boolean MobileValidation(String mobile) {
		User user = userDao.getUserByMobile(mobile);

		if (user != null) {
			return true;
		}
		else
		return false;
	}
	
	
	
	
	
}
