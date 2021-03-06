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

import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.travelModesMap;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.travelTypesMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.web.servlet.DispatcherServlet.INPUT_FLASH_MAP_ATTRIBUTE;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.abuabdul.mytravelpal.data.model.CalendarEvent;
import com.abuabdul.mytravelpal.data.model.MyTravelPalPlan;
import com.abuabdul.mytravelpal.data.service.MyTravelPalService;
import com.beust.jcommander.internal.Maps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 */
public class MyTravelPalLandingControllerTest {

	@Mock
	private MyTravelPalService myTravelPalService;

	@InjectMocks
	private MyTravelPalLandingController landingController;

	private MockMvc mockMvc;

	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(landingController).build();
	}

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(post("/")).andExpect(status().isOk()).andExpect(forwardedUrl("/index.jsp"));
	}

	@Test
	public void testMyTravelPal() throws Exception {
		mockMvc.perform(post("/travel/planBoard.go")).andExpect(status().isOk()).andExpect(view().name("myTravelPal"));
	}

	@Test
	public void testMyTravelPalPlan() throws Exception {
		Map<String, Object> inputFlashMap = Maps.newHashMap();
		inputFlashMap.put("travelPlanned", Boolean.TRUE);
		mockMvc.perform(post("/travel/plans.go").requestAttr(INPUT_FLASH_MAP_ATTRIBUTE, inputFlashMap))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("myTravelPalPlan", "travelPlanned", "travelModes", "travelTypes"))
				.andExpect(view().name("travel/plan/form"));
	}

	@Test
	public void testMyTravelPalMakePlan() throws Exception {
		mockMvc.perform(post("/secure/travel/makePlans.go").sessionAttr("myTravelPalPlan", new MyTravelPalPlan()))
				.andExpect(status().isFound()).andExpect(flash().attribute("travelPlanned", true))
				.andExpect(redirectedUrl("/travel/plans.go"));
	}

	@Test
	public void testMyTravelPalViewPlan() throws Exception {
		Map<String, Object> inputFlashMap = Maps.newHashMap();
		inputFlashMap.put("travelPlanRemoved", Boolean.TRUE);
		mockMvc.perform(post("/secure/travel/viewPlans.go").requestAttr(INPUT_FLASH_MAP_ATTRIBUTE, inputFlashMap))
				.andExpect(status().isOk()).andExpect(model().attributeExists("allTravelPlans", "travelPlanRemoved"))
				.andExpect(view().name("travel/plan/view/modify"));
	}

	@Test
	public void testMyTravelPalRemovePlan() throws Exception {
		mockMvc.perform(post("/secure/travel/12123123/removePlans.go")).andExpect(status().isFound())
				.andExpect(flash().attributeExists("travelPlanRemoved"))
				.andExpect(redirectedUrl("/secure/travel/viewPlans.go"));
	}

	@Test
	public void testMyTravelPalUpdatePlan() throws Exception {
		JSONObject json = new JSONObject();
		json.put("status", "success");
		mockMvc.perform(post("/secure/travel/updatePlans.go").param("name", "any_name").param("pk", "any_id")
				.param("value", "any_value")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().json(json.toString()))
				.andReturn();
	}

	@Test
	public void testMyTravelPalTravelMode() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = Maps.newHashMap();
		map.put("", "Select");
		map.putAll(travelModesMap);
		mockMvc.perform(post("/secure/travel/loadTravelMode.go")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(mapper.writeValueAsString(map))).andReturn();
	}

	@Test
	public void testMyTravelPalTravelType() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = Maps.newHashMap();
		map.put("", "Select");
		map.putAll(travelTypesMap);
		mockMvc.perform(post("/secure/travel/loadTravelType.go")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(mapper.writeValueAsString(map))).andReturn();
	}

	@Test
	public void testMyTravelPalEvents() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<CalendarEvent> events = Lists.newArrayList();
		mockMvc.perform(post("/secure/travel/planLoad.go")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(mapper.writeValueAsString(events))).andReturn();
	}
}