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

import com.slt.infobus.entity.IBSlotTrackerDtl;
import com.slt.infobus.repository.SlotTrackerDAO;

@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class SlotTrackerController {
	private Logger log = Logger.getLogger(SlotTrackerController.class);
	
	@Autowired
	private SlotTrackerDAO slotTrackerRepo;
	
	
	@RequestMapping(value="/slotTracker", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBSlotTrackerDtl> getAllBookings() {
        List<IBSlotTrackerDtl> allSlots = slotTrackerRepo.findAll();
        return  allSlots;
	}
	
	@RequestMapping(value="/slotTracker", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addSlotTracker(@RequestBody IBSlotTrackerDtl slotBooking ) {
		log.info("add slotBooking id::"+slotBooking);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
	        IBSlotTrackerDtl allSlots = slotTrackerRepo.save(slotBooking);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}
	
	@RequestMapping(value="/slotTracker", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updateSlotTracker(@RequestBody IBSlotTrackerDtl slotBooking ) {
		log.info("update slotBooking id::"+slotBooking);
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
	        IBSlotTrackerDtl allSlots = slotTrackerRepo.save(slotBooking);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}

	@RequestMapping(value="/slotTracker/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteBookedSlot(@PathVariable("id") int id) {
		log.info("delete slotBooking id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";
		try{
			IBSlotTrackerDtl loc = slotTrackerRepo.getSlotTracker(id);
	        slotTrackerRepo.delete(loc);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
   }
	
	@RequestMapping(value="/slotTracker/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBSlotTrackerDtl getBookedSlotById(@PathVariable int id) {
		log.info("get slotBooking id::"+id);
        IBSlotTrackerDtl allSlots = slotTrackerRepo.getSlotTracker(id);
        return  allSlots;
	}
	
}
