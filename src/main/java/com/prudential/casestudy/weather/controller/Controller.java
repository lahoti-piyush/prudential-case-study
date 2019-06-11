package com.prudential.casestudy.weather.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prudential.casestudy.weather.model.City;
import com.prudential.casestudy.weather.service.WeatherService;

@RestController
public class Controller {

	@Autowired
	WeatherService service;
	
	private static Logger log = LoggerFactory.getLogger(Controller.class);
	
	@RequestMapping(path="/upload", method=RequestMethod.POST) 
	public ResponseEntity<List<City>> upload(@RequestParam("file") MultipartFile file) {
		ResponseEntity<List<City>> response =null;
		try {
			List<City> cities = service.readFieStream(file.getInputStream());
			service.populateWeather(cities);
			response = new ResponseEntity<List<City>>(cities, HttpStatus.OK);
		} catch (IOException e) {
			log.error("Exception", e);
			response = new ResponseEntity<List<City>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
}
