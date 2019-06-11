package com.prudential.casestudy.weather.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prudential.casestudy.weather.model.City;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WeatherServiceImplTest {

	@Autowired
	WeatherServiceImpl weatherService;
	
	@Test
	public void getWeatherInfoTest() {
		City city = new City();
		city.setCityId(1275339);
		city.setCityName("Mumbai");
		Map<String, Object> res = weatherService.getWeatherInfo(city);
		assertNotNull(res);
	}
	
	@Test 
	public void writeToFileTest() throws Exception {
		List<City> cities = readFile();
		weatherService.writeToFile(cities);
	}
	
	@Test
	public void populateWeatherTest() throws IOException {
		List<City> cities = readFile();
		weatherService.populateWeather(cities);
	}
	
	@Test
	public void readFileTest() throws IOException {
		List<City> list = readFile();
		assertEquals(10, list.size());
	}
	
	private List<City> readFile() throws IOException {
		InputStream stream = new ClassPathResource("data/test10City").getInputStream();
		List<City> cities = weatherService.readFieStream(stream);
		return cities;
	}
}
