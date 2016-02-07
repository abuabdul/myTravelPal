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
package com.abuabdul.mytravelpal.data.model;

/**
 * @author abuabdul
 *
 */
public class MyTravelPalPlan {

	private String id;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String travelPlanDesc;
	private String travelMode;
	private String travelType;
	private String sideNote;
	private String createdBy;
	private String updatedBy;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the travelPlanDesc
	 */
	public String getTravelPlanDesc() {
		return travelPlanDesc;
	}

	/**
	 * @param travelPlanDesc
	 *            the travelPlanDesc to set
	 */
	public void setTravelPlanDesc(String travelPlanDesc) {
		this.travelPlanDesc = travelPlanDesc;
	}

	/**
	 * @return the travelMode
	 */
	public String getTravelMode() {
		return travelMode;
	}

	/**
	 * @param travelMode
	 *            the travelMode to set
	 */
	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}

	/**
	 * @return the travelType
	 */
	public String getTravelType() {
		return travelType;
	}

	/**
	 * @param travelType
	 *            the travelType to set
	 */
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	/**
	 * @return the sideNote
	 */
	public String getSideNote() {
		return sideNote;
	}

	/**
	 * @param sideNote
	 *            the sideNote to set
	 */
	public void setSideNote(String sideNote) {
		this.sideNote = sideNote;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
