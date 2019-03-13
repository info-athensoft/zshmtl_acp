package com.athensoft.content.event.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athensoft.content.event.entity.EventMedia;
import com.athensoft.content.event.service.EventMediaService;

import lombok.extern.log4j.Log4j;

/**
 * The controller of news media
 * 
 * @author Athens
 * @version 1.0
 */
@Controller
@RequestMapping("/events/media")
@Log4j
public class NewsMediaController {

	/**
	 * EventMedia Service instance
	 */
	@Autowired
	private EventMediaService eventMediaService;

	/**
	 * set current media to as a cover media and refresh all media objects
	 * 
	 * @param mediaId
	 *            the mediaId of current media
	 * @param eventUUID
	 *            the eventUUID of current event
	 * @return data table of updated media object
	 */
	@RequestMapping(value = "/setcover", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> setCoverMedia(@RequestParam long mediaId, @RequestParam String eventUUID) {
		log.info("entering... /events/media/setcover");

		// data - set cover primary state
		EventMedia previousPrimaryMedia = eventMediaService.getPrimaryMediaByEventUUID(eventUUID);
		if (previousPrimaryMedia != null) {
			previousPrimaryMedia.setPrimaryMedia(false);
			eventMediaService.updateEventMedia(previousPrimaryMedia);
		}
		log.info("mediaId=" + mediaId + ",eventUUID=" + eventUUID);

		EventMedia media = eventMediaService.getEventMediaByMediaId(mediaId);
		media.setPrimaryMedia(true);
		eventMediaService.updateEventMedia(media);

		// data - media
		List<EventMedia> listEventMedia = eventMediaService.getEventMediaByEventUUID(eventUUID);
		log.info("Length of EventReview entries: " + listEventMedia == null ? "NULL" : listEventMedia.size());

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("eventMediaList", listEventMedia);

		log.info("leaving... /events/media/setcover");
		return model;
	}
	
	
	
	@RequestMapping(value = "/removecover", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> removeCoverMedia(@RequestParam long mediaId, @RequestParam String eventUUID) {
		log.info("entering... /events/media/removecover");

		// data - set cover primary state
		
		eventMediaService.deleteEventMediaById(mediaId);

		// data - media
		List<EventMedia> listEventMedia = eventMediaService.getEventMediaByEventUUID(eventUUID);
		log.info("Length of EventReview entries: " + listEventMedia == null ? "NULL" : listEventMedia.size());

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("eventMediaList", listEventMedia);

		log.info("leaving... /events/media/removecover");
		return model;
	}

	/**
	 * @param mediaId
	 * @param eventUUID
	 * @return
	 */
	@RequestMapping(value = "/reloadEventMedia", produces = "application/json")
	@ResponseBody
	public Map<String, Object> reloadEventMedia(@RequestParam String eventUUID) {
		log.info("entering... /events/media/reloadEventMedia");

		// data - media
		List<EventMedia> listEventMedia = eventMediaService.getEventMediaByEventUUID(eventUUID);
		log.info("Length of EventReview entries: " + listEventMedia == null ? "NULL" : listEventMedia.size());

		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = mav.getModel();
		model.put("eventMediaList", listEventMedia);

		log.info("leaving... /events/media/reloadEventMedia");
		return model;
	}

	@RequestMapping(value = "/changeMediaName", method = RequestMethod.POST)
	@ResponseBody
	public void changeMediaName(@RequestParam String jsonObjString) {

		log.info("entering... /events/media/changeMediaName");

		JSONObject ic_job = new JSONObject(jsonObjString);

		// News news = new News();
		String mediaId = Integer.toString(ic_job.getInt("mediaId"));
		String eventUUID = ic_job.getString("eventUUID");
		String mediaName = ic_job.getString("mediaName");

		/* business logic */
		eventMediaService.changeMediaName(mediaId, eventUUID, mediaName);

		log.info("leaving... /events/media/changeMediaName");
		return;
	}

	@RequestMapping(value = "/changeMediaLabel", method = RequestMethod.POST)
	@ResponseBody
	public void changeMediaLabel(@RequestParam String jsonObjString) {
		log.info("entering... /events/media/changeMediaLabel");

		JSONObject ic_job = new JSONObject(jsonObjString);

		// News news = new News();
		String mediaId = Integer.toString(ic_job.getInt("mediaId"));
		String eventUUID = ic_job.getString("eventUUID");
		String mediaLabel = ic_job.getString("mediaLabel");

		/* business logic */
		eventMediaService.changeMediaLabel(mediaId, eventUUID, mediaLabel);

		log.info("leaving... /events/media/changeMediaLabel");
		return;
	}

	@RequestMapping(value = "/changeSortNumber", method = RequestMethod.POST)
	@ResponseBody
	public void changeSortNumber(@RequestParam String jsonObjString) {
		log.info("entering... /events/media/changeSortNumber");

		JSONObject ic_job = new JSONObject(jsonObjString);

		// News news = new News();
		String mediaId = Integer.toString(ic_job.getInt("mediaId"));
		String eventUUID = ic_job.getString("eventUUID");
		String sortNumber = ic_job.getString("sortNumber");

		/* business logic */
		eventMediaService.changeSortNumber(mediaId, eventUUID, sortNumber);

		log.info("leaving... /events/media/changeSortNumber");
		return;
	}

}
