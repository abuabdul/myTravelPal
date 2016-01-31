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
package com.abuabdul.mytravelpal.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.abuabdul.mytravelpal.data.document.MyTravelPal;
import com.abuabdul.mytravelpal.data.model.MyTravelPalPlan;
import com.google.common.base.Function;

/**
 * @author abuabdul
 *
 */
public class MyTravelPalFunc {

	public static Function<MyTravelPalPlan, MyTravelPal> fromTravelPlanToTravelPal = new Function<MyTravelPalPlan, MyTravelPal>() {
		@Override
		public MyTravelPal apply(MyTravelPalPlan plan) {
			MyTravelPal myTravelPal = new MyTravelPal();
			myTravelPal.setCreatedBy(plan.getCreatedBy());
			myTravelPal.setCreatedDate(String.valueOf(getUTCDateTime().getTime()));
			myTravelPal.setUpdatedBy(plan.getUpdatedBy());
			myTravelPal.setUpdatedDate(String.valueOf(getUTCDateTime().getTime()));
			// TODO fill all the plans
			return myTravelPal;
		}
	};

	public static Function<MyTravelPal, MyTravelPalPlan> fromTravelPalToTravelPlan = new Function<MyTravelPal, MyTravelPalPlan>() {
		@Override
		public MyTravelPalPlan apply(MyTravelPal plan) {
			MyTravelPalPlan travel = new MyTravelPalPlan();
			// TODO fill all the plans
			return travel;
		}
	};

	public static Date getUTCDateTime() {
		return new DateTime(DateTimeZone.UTC).toDate();
	}

}
