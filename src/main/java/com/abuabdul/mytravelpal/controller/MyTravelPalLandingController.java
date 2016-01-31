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

import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.fromTravelPlanToTravelPal;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abuabdul.mytravelpal.data.document.MyTravelPal;
import com.abuabdul.mytravelpal.data.model.MyTravelPalPlan;
import com.abuabdul.mytravelpal.data.service.MyTravelPalService;

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

	@RequestMapping(value = "/travel/plans.go")
	public String myTravelPalPlan(ModelMap model, HttpServletRequest request) {
		log.debug("Entering myTravelPalPlan() in " + this.getClass().getName());
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			model.addAttribute("travelPlanned", inputFlashMap.get("travelPlanned"));
		}
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

}
