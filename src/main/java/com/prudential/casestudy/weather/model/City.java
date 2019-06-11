package com.prudential.casestudy.weather.model;

import java.util.Map;

public class City {

	private int cityId;
	private String cityName;
	private Map<String, Object> weather;
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Map<String, Object> getWeather() {
		return weather;
	}
	public void setWeather(Map<String, Object> weather) {
		this.weather = weather;
	}
}
