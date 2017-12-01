package com.slt.infobus.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IBPlayAudit {
	private final Logger logger = LoggerFactory.getLogger(IBPlayAudit.class);
	public void playAuditLog(String msg){//String videoName, String playTime, String status){
		logger.debug(msg);//videoName,playTime,status);
	}
}
