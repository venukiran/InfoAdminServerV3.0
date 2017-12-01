
package com.slt.infobus.util;

import java.rmi.server.UID;
import java.util.UUID;

/**
 * @author saikrishna
 *
 */
public class GenerateUUID {
	public static String generate() {
		UUID uid = UUID.randomUUID();
		return String.valueOf(uid).length()>0?String.valueOf(uid):null;
	}
	public static UID UId(){
		 UID uid = new UID();
	      System.out.println("User Id: " + uid);
	      return uid;
	}
}