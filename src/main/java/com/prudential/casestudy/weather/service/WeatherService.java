package com.prudential.casestudy.weather.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.prudential.casestudy.weather.model.City;

public interface WeatherService {

	List<City> populateWeather(List<City> cities);

	Map<String, Object> getWeatherInfo(City city);

	List<City> readFieStream(InputStream inputStream) throws IOException;

}
