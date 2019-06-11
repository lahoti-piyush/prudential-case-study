package com.prudential.casestudy.weather.ws;

import java.util.Map;

public interface OpenWeatherService {

	Map<String, Object> getWeather(int cityId);

}
