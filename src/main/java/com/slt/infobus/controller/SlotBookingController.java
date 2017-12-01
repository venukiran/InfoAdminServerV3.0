package com.slt.infobus.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slt.infobus.entity.IBSlotBookingDtl;
import com.slt.infobus.entity.IBSlotTrackerDtl;
import com.slt.infobus.entity.IBVideoDtl;
import com.slt.infobus.repository.SlotBookingDAO;
import com.slt.infobus.repository.SlotTrackerDAO;
import com.slt.infobus.repository.VideoDAO;
import com.slt.infobus.util.SlotBookings;
import com.slt.infobus.util.SlotDetails;

@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class SlotBookingController {
	private Logger log = Logger.getLogger(SlotBookingController.class);

	@Autowired
	private SlotBookingDAO slotBookRepo;
	
	@Autowired
	private SlotTrackerDAO slotTrackerRepo;
	
	@Autowired
	private VideoDAO videoRepo;
	
	
	@RequestMapping(value="/bookslots", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBSlotBookingDtl> getAllBookings() {
        List<IBSlotBookingDtl> allSlots = slotBookRepo.findAll();
        return  allSlots;
	}
	
	@RequestMapping(value="/bookslots", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addBookedSlot(@RequestBody IBSlotBookingDtl slotBooking ) {
		slotBooking.setBookedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		log.info("add slotBooking ::"+slotBooking.toString());
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";		
		try{
			slotBooking = slotBookRepo.save(slotBooking);
			String[] locations = slotBooking.getLocationId().split(",");
			long totDays = (slotBooking.getEndDate().getTime() - slotBooking.getStartDate().getTime())/(60*60*24*1000);
			Date startDate = slotBooking.getStartDate();
			for (String loc : locations) {
				int a=0;
				startDate = slotBooking.getStartDate();
				HashMap<Integer,IBSlotTrackerDtl> trackMap = new HashMap<>();
				for(int dt=0;dt<=totDays;dt++){
				Calendar cal =Calendar.getInstance();
				cal.setTimeInMillis(startDate.getTime());
				cal.add(Calendar.DAY_OF_MONTH, a);
				//log.info("date::"+cal));
				a=1;
				startDate = new Date(cal.getTimeInMillis());				
				List<SlotDetails> slotList = slotTrackerRepo.findAllSlotsByGroup(loc, slotBooking.getStartTime().toString(), slotBooking.getEndTime().toString(),startDate);
				IBVideoDtl video = videoRepo.getVideo(slotBooking.getVideoId());				
				int stHr = slotBooking.getStartTime().getHours();
				int enHr = 0;
				if(slotBooking.getEndTime().getHours() == 0){
					enHr = 24;
				}else{
					enHr = slotBooking.getEndTime().getHours();
				}
				int totHrs = enHr - stHr;
				int repeatsPerHr = 1;
				if(totHrs>0){
					 int rp = (slotBooking.getRepeatsPerDay()/totHrs);
					 if(rp>0){
						 repeatsPerHr = rp;
					 }else{
						 repeatsPerHr = 1;
					 }					 
				}else{
					repeatsPerHr = slotBooking.getRepeatsPerDay();
				}
				
				int balRepeats = slotBooking.getRepeatsPerDay() - (totHrs*repeatsPerHr);
				
				//int slots = video.getPlayTime() /10)
				//TODO: fix it based on playTime
				//Sep-4th-2017 1:41 AM fixing the issue on emergency
				int slot = 1;
				try{
					float fl = Float.parseFloat(video.getPlayTime());
					log.info("PlayTime::"+fl);
					if(fl<=10.0){
						slot = 1;
					}else{
						slot = ((int)(fl/10)) + 1;
					}
					
				}catch(Exception ex){
					log.info(ex.getMessage());
				}
				
				int slotsPerHr = repeatsPerHr * slot;

				IBSlotTrackerDtl tracker = null; //new IBSlotTrackerDtl();
				trackMap = new HashMap<>();
				if(!slotList.isEmpty()){
					int bal = 0;
					for (SlotDetails slotDetails : slotList) {
						tracker = new IBSlotTrackerDtl();
						if(slotDetails.getMinBalSlots()>0){
							bal=bal+slotsPerHr;
							tracker.setSlotTime(slotDetails.getSlotTime());
							tracker.setAvailableSlots(slotDetails.getMinBalSlots());
							if(slotDetails.getMinBalSlots()>=(bal)){
								tracker.setBalanceSlots(slotDetails.getMinBalSlots()-bal);
								tracker.setBookedSlots(bal);
								bal=0;
							}else{
								bal=slotsPerHr-slotDetails.getMinBalSlots();
								tracker.setBalanceSlots(0);
								tracker.setBookedSlots(slotDetails.getMinBalSlots());
							}								
							
							tracker.setLocationId(Integer.parseInt(loc));
							tracker.setRepeatsPerHr(repeatsPerHr);
							tracker.setSlotBookId(slotBooking.getSlotBookId());
							tracker.setSlotDate(startDate);
							tracker.setVideoId(slotBooking.getVideoId());
							trackMap.put(slotDetails.getSlotTime().getHours(), tracker);
						}else{
							log.info("No Slots are available");
						}
					}//for
				}//if
				
				for(int t=stHr;t<=enHr;t++){
					if(t==enHr){
						repeatsPerHr = balRepeats;
						slotsPerHr = repeatsPerHr * slot;
					}
					if(t==24){t=00;}
					if(!trackMap.containsKey(t)){
						tracker = new IBSlotTrackerDtl();
						tracker.setSlotTime(new java.sql.Time(t,0,0));
						tracker.setAvailableSlots(180); //Minimum slots per hours
						tracker.setBalanceSlots(180-slotsPerHr);
						tracker.setBookedSlots(slotsPerHr);						
						tracker.setLocationId(Integer.parseInt(loc));
						tracker.setRepeatsPerHr(repeatsPerHr);
						tracker.setSlotBookId(slotBooking.getSlotBookId());
						tracker.setSlotDate(startDate);
						tracker.setVideoId(slotBooking.getVideoId());
						trackMap.put(t, tracker);
					}
				}
				
			
			//save the data in tracker
				Iterator itor = trackMap.keySet().iterator();
				IBSlotTrackerDtl slotTracker=null;
				while(itor.hasNext()){
					slotTracker = trackMap.get(itor.next());
					slotTrackerRepo.save(slotTracker);
				}
		  }//date for
		}//date loc
		}catch(Exception ex){
			log.info("Failed Slot Booking", ex);
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
			ex.printStackTrace();			
		}
	    return  msg;
	}
	
	@RequestMapping(value="/bookslots", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updateBookedSlot(@RequestBody IBSlotBookingDtl slotBooking ) {
		log.info("update slotBooking id::"+slotBooking);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
			IBSlotBookingDtl allSlots = slotBookRepo.save(slotBooking);
			
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}

	@RequestMapping(value="/bookslots/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteBookedSlot(@PathVariable("id") int id) {
		log.info("delete slotBooking id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";
		try{
			IBSlotBookingDtl slot = slotBookRepo.getSlotBooking(id);
	        slotBookRepo.delete(slot);
	        //slot tracker details also
	        slotTrackerRepo.deleteAllByBookId(id);	        
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;        
	}
	
	@RequestMapping(value="/bookslots/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBSlotBookingDtl getBookedSlotById(@PathVariable int id) {
		log.info("get slotBooking id::"+id);
        IBSlotBookingDtl allSlots = slotBookRepo.getSlotBooking(id);
        return  allSlots;
	}
	
}
