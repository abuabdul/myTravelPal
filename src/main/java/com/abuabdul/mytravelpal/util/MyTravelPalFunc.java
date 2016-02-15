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

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.abuabdul.mytravelpal.data.document.MyTravelPal;
import com.abuabdul.mytravelpal.data.model.CalendarEvent;
import com.abuabdul.mytravelpal.data.model.MyTravelPalPlan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author abuabdul
 *
 */
public class MyTravelPalFunc {

	private static final Logger log = LogManager.getLogger(MyTravelPalFunc.class.getName());

	public static final Function<MyTravelPalPlan, MyTravelPal> fromTravelPlanToTravelPal = new Function<MyTravelPalPlan, MyTravelPal>() {
		@Override
		public MyTravelPal apply(MyTravelPalPlan plan) {
			MyTravelPal myTravelPal = new MyTravelPal();
			myTravelPal.setTravelPlanDesc(plan.getTravelPlanDesc());
			myTravelPal.setStartDate(plan.getStartDate());
			myTravelPal.setStartTime(plan.getStartTime());
			myTravelPal.setEndDate(plan.getEndDate());
			myTravelPal.setEndTime(plan.getEndTime());
			myTravelPal.setTravelMode(plan.getTravelMode());
			myTravelPal.setTravelType(plan.getTravelType());
			myTravelPal.setSideNote(plan.getSideNote());
			myTravelPal.setCreatedBy(getUser());
			myTravelPal.setCreatedDate(getFormattedUTCDateTime());
			myTravelPal.setUpdatedBy(getUser());
			myTravelPal.setUpdatedDate(getFormattedUTCDateTime());
			return myTravelPal;
		}
	};

	public static final Function<MyTravelPal, MyTravelPalPlan> fromTravelPalToTravelPlan = new Function<MyTravelPal, MyTravelPalPlan>() {
		@Override
		public MyTravelPalPlan apply(MyTravelPal plan) {
			MyTravelPalPlan travel = new MyTravelPalPlan();
			travel.setId(plan.getId());
			travel.setTravelPlanDesc(plan.getTravelPlanDesc());
			travel.setStartDate(plan.getStartDate());
			travel.setStartTime(plan.getStartTime());
			travel.setEndDate(plan.getEndDate());
			travel.setEndTime(plan.getEndTime());
			travel.setTravelMode(plan.getTravelMode());
			travel.setTravelType(plan.getTravelType());
			travel.setSideNote(plan.getSideNote());
			travel.setCreatedBy(plan.getCreatedBy());
			travel.setUpdatedBy(plan.getUpdatedBy());
			return travel;
		}
	};

	public static final List<CalendarEvent> fromTravelPalToCalendarEvent(List<MyTravelPal> plans)
			throws ParseException {
		return Lists.transform(plans, new Function<MyTravelPal, CalendarEvent>() {
			@Override
			public CalendarEvent apply(MyTravelPal plan) {
				CalendarEvent event = new CalendarEvent();
				try {
					event.setId(plan.getId());
					event.setTitle(plan.getTravelPlanDesc());
					event.setStart(dateISO8601(plan.getStartDate())
							+ (isNotEmpty(plan.getStartTime()) ? "T" + plan.getStartTime() : ""));
					event.setEnd(isNotEmpty(plan.getEndDate()) ? dateISO8601(plan.getEndDate())
							+ (isNotEmpty(plan.getEndTime()) ? "T" + plan.getEndTime() : "") : "");
					event.setEndTimeHoverMsg(plan.getEndTime());
					event.setTravelMode(plan.getTravelMode());
					event.setTravelType(plan.getTravelType());
					event.setSideNote(plan.getSideNote());
				} catch (ParseException ex) {
					log.warn("Cannot parse corrupt dates. " + ex.getMessage());
				}
				return event;
			}
		});
	}

	public static final String eventJson(Object object) throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		String jsonEventObjects = mapper.writeValueAsString(object);
		log.info("Event objects JSON :" + jsonEventObjects);
		return jsonEventObjects;
	}

	public static final List<String> travelModes = Lists.transform(Arrays.asList(MyTravelPalMode.values()),
			new Function<MyTravelPalMode, String>() {
				@Override
				public String apply(MyTravelPalMode mode) {
					return mode.toString();
				}
			});

	public static final List<String> travelTypes = Lists.transform(Arrays.asList(MyTravelPalType.values()),
			new Function<MyTravelPalType, String>() {
				@Override
				public String apply(MyTravelPalType type) {
					return type.toString();
				}
			});

	public static final Map<String, String> travelModesMap = Maps.toMap(travelModes, new Function<String, String>() {
		@Override
		public String apply(String value) {
			return value;
		}
	});

	public static final Map<String, String> travelTypesMap = Maps.toMap(travelTypes, new Function<String, String>() {
		@Override
		public String apply(String value) {
			return value;
		}
	});

	public static final Date getUTCDateTime() {
		return new DateTime(DateTimeZone.UTC).toDate();
	}

	public static final String getFormattedUTCDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(getUTCDateTime());
	}

	public static final String getUser() {
		return "abuabdul";
	}

	public static String dateISO8601(String dateStr) throws ParseException {
		final SimpleDateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = ISO8601.parse(dateStr);
		return ISO8601.format(date);
	}

	public static final Iterable<Field> privateMandatoryFields = Iterables.filter(privateFieldsOfMyTravelPlan(),
			new Predicate<Field>() {
				@Override
				public boolean apply(Field field) {
					return Modifier.isPrivate(field.getModifiers()) && mandatoryFields().contains(field.getName());
				}
			});

	public static final List<String> privateMandatoryFieldsList = Lists
			.transform(Lists.newArrayList(privateMandatoryFields), new Function<Field, String>() {
				@Override
				public String apply(Field field) {
					return field.getName();
				}
			});

	public static final boolean isMandatoryField(String name) {
		if (isNotEmpty(name)) {
			return privateMandatoryFieldsList.contains(name);
		}
		return false;
	}

	public static final List<Field> privateFieldsOfMyTravelPlan() {
		Field[] declaredFields = MyTravelPalPlan.class.getDeclaredFields();
		return Arrays.asList(declaredFields);
	}

	public static final List<String> mandatoryFields() {
		return Lists.transform(Arrays.asList(Mandatory.values()), new Function<Mandatory, String>() {
			@Override
			public String apply(Mandatory field) {
				return field.toString();
			}
		});
	}

	public static enum Mandatory {
		TRAVELPLAN("travelPlanDesc"), STARTDATE("startDate"), TRAVELMODE("travelMode"), TRAVELTYPE("travelType");

		private String mandatoryField;

		Mandatory(String field) {
			this.mandatoryField = field;
		}

		@Override
		public String toString() {
			return this.mandatoryField;
		}
	}
}
