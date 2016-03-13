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
package com.abuabdul.mytravelpal.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.abuabdul.mytravelpal.interceptor.MyTravelPalCorsInterceptor;

/**
 * @author abuabdul
 *
 */
@Configuration
public class MyTravelPalWebConfig {

	@Value("${mytravelpal.app.origins}")
	private String origins;

	public MyTravelPalWebConfig() {
	}

	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
				new DispatcherServlet(webContext));
		servletRegistrationBean.setName("mytravelpal-spring");
		servletRegistrationBean.setLoadOnStartup(1);
		servletRegistrationBean.addUrlMappings("/", "*.go");
		return servletRegistrationBean;
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/static-content/")
						.setCachePeriod(31557600);
			}

			@Override
			public void configureViewResolvers(ViewResolverRegistry registry) {
				registry.tiles().viewClass(TilesView.class);
				registry.jsp("/WEB-INF/jsp/", ".jsp").viewClass(JstlView.class);
			}

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new MyTravelPalCorsInterceptor(origins)).addPathPatterns("/secure/**")
						.excludePathPatterns("/resources/**");
			}

		};
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/tiles-def.xml");
		return tilesConfigurer;
	}
}
