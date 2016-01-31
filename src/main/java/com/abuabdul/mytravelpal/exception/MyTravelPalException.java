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
package com.abuabdul.mytravelpal.exception;

/**
 * @author abuabdul
 *
 */
public class MyTravelPalException extends Exception {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 345456L;

	public MyTravelPalException() {

	}

	public MyTravelPalException(String message) {
		super(message);
	}

	public MyTravelPalException(Throwable cause) {
		super(cause);
	}

	public MyTravelPalException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyTravelPalException(String message, Error error) {
		super(message, error);
	}
}
