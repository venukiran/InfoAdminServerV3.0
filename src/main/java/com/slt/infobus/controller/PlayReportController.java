package com.slt.infobus.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slt.infobus.entity.IBLocationDtl;
import com.slt.infobus.entity.IBPlayReportDtl;
import com.slt.infobus.entity.IBScreenDtl;
import com.slt.infobus.repository.PlayReportDAO;
import com.slt.infobus.repository.ScreenDAO;

@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class PlayReportController {
	private Logger log = Logger.getLogger(ScreenController.class);

	@Autowired
	private PlayReportDAO playRepo;
	
	
	
	@RequestMapping(value="/playdata", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public List<IBPlayReportDtl> addScreen(@RequestBody JSONObject json  ) {
		String stDate = (String)json.get("startDate");
		String enDate = (String)json.get("endDate");
		String loc = (String)json.get("locationName");
		log.info("Startdate::"+stDate);
		log.info("Enddate::"+enDate);
		log.info("locationName::"+loc);
		List<IBPlayReportDtl> reportData = null;
		try{
			if(stDate!=null && enDate!=null && loc!=null){
				reportData = playRepo.getPlayReportData(stDate, enDate, loc);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
        return  reportData;
	}
	
}
