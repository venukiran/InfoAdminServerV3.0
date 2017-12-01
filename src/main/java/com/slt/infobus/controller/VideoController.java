package com.slt.infobus.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slt.infobus.entity.IBVideoDtl;
import com.slt.infobus.repository.VideoDAO;


@CrossOrigin(origins = {"http://localhost", "http://localhost:80" }, maxAge = 4800, allowCredentials = "false", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
public class VideoController {

	private Logger log = Logger.getLogger(VideoController.class);
	
	@Autowired
	private VideoDAO videoRepo;
	
	
	@RequestMapping(value="/videos", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public List<IBVideoDtl> getAllVideos() {
        List<IBVideoDtl> allVideos = videoRepo.findAll();
        return  allVideos;
	}
	
	@RequestMapping(value="/videos", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json")
	public String addVideo(@RequestBody IBVideoDtl video ) {
		video.setUploadedDate((new java.sql.Date(Calendar.getInstance().getTimeInMillis())));
		log.info("add video::"+video.toString());
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
	        IBVideoDtl allVideos = videoRepo.save(video);
	    }catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}
	
	@RequestMapping(value="/videos", method=RequestMethod.PUT, headers="Accept=application/json", produces="application/json")
	public String updateVideo(@RequestBody IBVideoDtl video ) {
		log.info("update video::"+video.toString());
		String msg = "{\"statusCode\":200, \"Msg\":\"ADDED\"}";
		try{
	        IBVideoDtl allVideos = videoRepo.save(video);
	    }catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
	    return  msg;
	}

	@RequestMapping(value="/videos/{id}", method=RequestMethod.DELETE, headers="Accept=application/json", produces="application/json")
	public String deleteVideo(@PathVariable("id") int id) {
		log.info("delete video id::"+id);
		String msg = "{\"statusCode\":200, \"Msg\":\"DELETED\"}";
		try{
		IBVideoDtl loc = videoRepo.getVideo(id);
        videoRepo.delete(loc);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "{\"statusCode\":500, \"Msg\":\"ERROR\"}";
		}
        return  msg;
	}
	
	@RequestMapping(value="/videos/{id}", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public IBVideoDtl getVideoById(@PathVariable int id) {
		log.info("get video id::"+id);
        IBVideoDtl allVideos = videoRepo.getVideo(id);
        return  allVideos;
	}
	
}
