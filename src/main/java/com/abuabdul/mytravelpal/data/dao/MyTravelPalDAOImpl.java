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

import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.getFormattedUTCDateTime;
import static com.abuabdul.mytravelpal.util.MyTravelPalFunc.getUser;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.abuabdul.mytravelpal.data.document.MyTravelPal;

/**
 * @author abuabdul
 *
 */
public class MyTravelPalDAOImpl implements MyTravelPalDAO {

	private final MongoTemplate mongoTemplate;

	public MyTravelPalDAOImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void saveTravel(MyTravelPal plan) {
		mongoTemplate.save(plan);
	}

	@Override
	public List<MyTravelPal> listAllTravelPlans() {
		return mongoTemplate.findAll(MyTravelPal.class);
	}

	@Override
	public void updateTravelPlan(String id, String key, Object val) {
		mongoTemplate.updateFirst(query(id), update(key, val), MyTravelPal.class);
	}

	@Override
	public void removeTravelPlan(MyTravelPal plan) {
		mongoTemplate.remove(plan);
	}

	@Override
	public long countTravelPlans() {
		return mongoTemplate.count(query(), MyTravelPal.class);
	}

	protected Query query(String id) {
		return new Query(where("id").is(id));
	}

	protected Query query() {
		return new Query();
	}

	protected Update update(String key, Object val) {
		Update update = new Update();
		update.set(key, val);
		update.set("updatedDate", getFormattedUTCDateTime());
		update.set("updatedBy", getUser());
		return update;
	}
}
