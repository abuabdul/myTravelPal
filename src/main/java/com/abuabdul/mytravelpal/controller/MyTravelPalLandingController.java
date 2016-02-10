/*
 * Copyright 2013-2016 abuabdul.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.abuabdul.mytravelpal.controller;

import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.fromTravelPalToCalendarEvent;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.fromTravelPalToTravelPlan;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.fromTravelPlanToTravelPal;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.isMandatoryField;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.travelModes;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.travelModesMap;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.travelTypes;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.travelTypesMap;
import static com.google.common.collect.Lists.transform;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abuabdul.mytravelpal.data.document.MyTravelPal;
import com.abuabdul.mytravelpal.data.model.MyTravelPalPlan;
import com.abuabdul.mytravelpal.data.service.MyTravelPalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * @author abuabdul
 *
 */
@Controller
public class MyTravelPalLandingController {

	private static final Logger log = LogManager.getLogger(MyTravelPalLandingController.class.getName());

	@Autowired
	private MyTravelPalService myTravelPalService;

	@RequestMapping(value = "/")
	public String index(ModelMap model) {
		return "forward:/index.jsp";
	}

	@RequestMapping(value = "/travel/planBoard.go")
	public String myTravelPal(ModelMap model) {
		return "myTravelPal";
	}

	@RequestMapping(value = "/secure/travel/planLoad.go", produces = "application/json")
	@ResponseBody
	public String myTravelPalEvents(ModelMap model) throws JsonProcessingException {
		log.debug("Entering myTravelPalEvents() in " + this.getClass().getName());
		List<MyTravelPal> travelPlans = myTravelPalService.retrieveAllTravelPlans();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(fromTravelPalToCalendarEvent(travelPlans));
		System.out.println(json);
		return json;
	}

	@RequestMapping(value = "/travel/plans.go")
	public String myTravelPalPlan(ModelMap model, HttpServletRequest request) {
		log.debug("Entering myTravelPalPlan() in " + this.getClass().getName());
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			model.addAttribute("travelPlanned", inputFlashMap.get("travelPlanned"));
		}
		model.addAttribute("travelModes", travelModes);
		model.addAttribute("travelTypes", travelTypes);
		model.addAttribute("myTravelPalPlan", new MyTravelPalPlan());
		return "travel/plan/form";
	}

	@RequestMapping(value = "/secure/travel/makePlans.go")
	public String myTravelPalMakePlan(@ModelAttribute("myTravelPalPlan") MyTravelPalPlan plan,
			RedirectAttributes redirectAttrs) {
		log.debug("Entering myTravelPalMakePlan() in " + this.getClass().getName());
		MyTravelPal travel = fromTravelPlanToTravelPal.apply(plan);
		myTravelPalService.makeTravelPlans(travel);
		redirectAttrs.addFlashAttribute("travelPlanned", true);
		return "redirect:/travel/plans.go";
	}

	@RequestMapping(value = "/secure/travel/viewPlans.go")
	public String myTravelPalViewPlan(ModelMap model, HttpServletRequest request) {
		log.debug("Entering myTravelPalViewPlan() in " + this.getClass().getName());
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			model.addAttribute("travelPlanRemoved", inputFlashMap.get("travelPlanRemoved"));
		}
		List<MyTravelPalPlan> allTravelPlans = transform(myTravelPalService.retrieveAllTravelPlans(),
				fromTravelPalToTravelPlan);
		model.addAttribute("allTravelPlans", allTravelPlans);
		return "travel/plan/view/modify";
	}

	@RequestMapping(value = "/secure/travel/{id}/removePlans.go")
	public String myTravelPalRemovePlan(ModelMap model, @PathVariable String id, RedirectAttributes redirectAttrs) {
		log.debug("Entering myTravelPalRemovePlan() in " + this.getClass().getName());
		MyTravelPal plan = new MyTravelPal();
		plan.setId(id);
		myTravelPalService.removeTravelPlan(plan);
		redirectAttrs.addFlashAttribute("travelPlanRemoved", true);
		return "redirect:/secure/travel/viewPlans.go";
	}

	@RequestMapping(value = "/secure/travel/updatePlans.go", produces = "application/json")
	@ResponseBody
	public String myTravelPalUpdatePlan(HttpServletResponse response, @RequestParam String pk,
			@RequestParam String name, @RequestParam String value) {
		log.debug("Entering myTravelPalUpdatePlan() in " + this.getClass().getName());
		response.setStatus(HttpServletResponse.SC_OK);
		JSONObject json = new JSONObject();
		if (isEmpty(pk) || isEmpty(name)) {
			json.put("status", "error");
			json.put("msg", "cannot update");
			return json.toString();
		}
		if (isMandatoryField(name) && isEmpty(value)) {
			json.put("status", "error");
			json.put("msg", "cannot be empty");
			return json.toString();
		}
		myTravelPalService.updateTravelPlan(pk, name, value);
		json.put("status", "success");
		return json.toString();
	}

	@RequestMapping(value = "/secure/travel/loadTravelMode.go", produces = "application/json")
	@ResponseBody
	public String myTravelPalTravelMode(HttpServletResponse response) throws JsonProcessingException {
		log.debug("Entering myTravelPalTravelMode() in " + this.getClass().getName());
		Map<String, String> map = Maps.newHashMap();
		map.put("", "Select");
		map.putAll(travelModesMap);
		response.setStatus(HttpServletResponse.SC_OK);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(map);
	}

	@RequestMapping(value = "/secure/travel/loadTravelType.go", produces = "application/json")
	@ResponseBody
	public String myTravelPalTravelType(HttpServletResponse response) throws JsonProcessingException {
		log.debug("Entering myTravelPalTravelType() in " + this.getClass().getName());
		Map<String, String> map = Maps.newHashMap();
		map.put("", "Select");
		map.putAll(travelTypesMap);
		response.setStatus(HttpServletResponse.SC_OK);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(map);
	}
}