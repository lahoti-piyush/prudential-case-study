package com.prudential.casestudy.weather.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prudential.casestudy.weather.model.City;
import com.prudential.casestudy.weather.ws.OpenWeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	OpenWeatherService openWeatherService;

	@Value("${output.path}")
	String outputPath;

	@Value("${output.prefix}")
	String prefix;

	private static Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

	/**
	 * get weather for city from open weather service
	 * 
	 * @param city
	 * @return
	 */
	@Override
	public Map<String, Object> getWeatherInfo(City city) {
		return openWeatherService.getWeather(city.getCityId());
	}

	@Override
	public List<City> populateWeather(List<City> cities) {

		for (City city : cities) {
			Map<String, Object> weather = getWeatherInfo(city);
			city.setWeather(weather);
		}

		try {
			writeToFile(cities);
		} catch (IOException e) {
			log.error("Exception", e);
		}
		return cities;
	}

	/**
	 * write list of city in JSON to output path with current time stamp
	 * 
	 * @param cities
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void writeToFile(List<City> cities) throws JsonGenerationException, JsonMappingException, IOException {
		long timestamp = Calendar.getInstance().getTimeInMillis();
		ObjectMapper om = new ObjectMapper();
		File dir = new File(outputPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String path = outputPath + File.separator + prefix + "_" + timestamp;
		log.info("writing output at " + path);
		File resultFile = new File(path);
		om.writeValue(resultFile, cities);
	}

	@Override
	public List<City> readFieStream(InputStream inputStream) throws IOException {
		List<City> cities = new LinkedList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] linePart = line.split("=");
				City city = new City();
				city.setCityId(Integer.parseInt(linePart[0]));
				city.setCityName(linePart[1]);
				cities.add(city);
			}
		}
		return cities;
	}

}
