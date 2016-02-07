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
package com.abuabdul.mytravelpal.data.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.abuabdul.mytravelpal.data.document.MyTravelPal;

/**
 * @author abuabdul
 *
 */
public class MyTravelPalDAOTest {

	@Mock
	private MongoTemplate mongoTemplate;

	@InjectMocks
	private MyTravelPalDAOImpl myTravelPalDAO;

	@BeforeClass
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveTravel() {
		MyTravelPal plan = new MyTravelPal();
		myTravelPalDAO.saveTravel(plan);
		verify(mongoTemplate).save(plan);
	}

	@Test
	public void testListAllTravelPlans() {
		myTravelPalDAO.listAllTravelPlans();
		verify(mongoTemplate).findAll(MyTravelPal.class);
	}

	@Test
	public void testUpdateTravelPlan() {
		myTravelPalDAO.updateTravelPlan("pk", "name", "someValue");
		verify(mongoTemplate).updateFirst(any(Query.class), any(Update.class), any(Class.class));
	}

	@Test
	public void testRemoveTravelPlan() {
		MyTravelPal plan = new MyTravelPal();
		myTravelPalDAO.removeTravelPlan(plan);
		verify(mongoTemplate).remove(plan);
	}

	@Test
	public void testCountTravelPlans() {
		myTravelPalDAO.countTravelPlans();
		verify(mongoTemplate).count(new Query(), MyTravelPal.class);
	}
}
