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

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author abuabdul
 *
 */
@Controller
public class MyTravelPalErrorController implements ErrorController {

	private final String ERROR_PATH = "/error";
	private final String ERROR_VIEW = "generic/error";

	@Override
	public String getErrorPath() {
		return this.ERROR_PATH;
	}

	@RequestMapping(value = ERROR_PATH)
	public String error(HttpServletRequest request) {
		return ERROR_VIEW;
	}
}
