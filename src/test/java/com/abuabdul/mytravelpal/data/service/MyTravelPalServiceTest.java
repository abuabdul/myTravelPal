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
package com.abuabdul.mytravelpal.data.service;

import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.abuabdul.mytravelpal.data.dao.MyTravelPalDAO;
import com.abuabdul.mytravelpal.data.document.MyTravelPal;

/**
 * @author abuabdul
 *
 */
public class MyTravelPalServiceTest {

	@Mock
	private MyTravelPalDAO myTravelPalDAO;

	@InjectMocks
	private MyTravelPalServiceImpl myTravelPalService;

	@BeforeClass
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMakeTravelPlans() throws Exception {
		MyTravelPal travel = new MyTravelPal();
		myTravelPalService.makeTravelPlans(travel);
		verify(myTravelPalDAO).saveTravel(travel);
	}

	@Test
	public void testRetrieveAllTravelPlans() throws Exception {
		myTravelPalService.retrieveAllTravelPlans();
		verify(myTravelPalDAO).listAllTravelPlans();
	}

	@Test
	public void testRemoveTravelPlan() {
		MyTravelPal plan = new MyTravelPal();
		myTravelPalService.removeTravelPlan(plan);
		verify(myTravelPalDAO).removeTravelPlan(plan);
	}

	@Test
	public void testUpdateTravelPlan() {
		myTravelPalService.updateTravelPlan("pk", "somekey", "somevalue");
		verify(myTravelPalDAO).updateTravelPlan("pk", "somekey", "somevalue");
	}

	@Test
	public void testCountTravelPlans() throws Exception {
		myTravelPalService.countTravelPlans();
		verify(myTravelPalDAO).countTravelPlans();
	}
}
