package com.prudential.casestudy.weather.ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

	@Autowired
	RestTemplate restTempalte;

	@Value("${openWeather.endpoint}")
	private String endpoint;

	@Value("${openWeather.appId}")
	private String appId;

	private static Logger log = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);

	@Override
	public Map<String, Object> getWeather(int cityId) {
		log.debug("get weather for City Id " + cityId);
		Map<String, Object> weatherDetails = new LinkedHashMap<>();
		try {
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("id", String.valueOf(cityId));
			uriVariables.put("appid", appId);
			ResponseEntity<String> response = restTempalte.getForEntity(endpoint + "?id={id}&appid={appid}",
					String.class, uriVariables);
			if (HttpStatus.OK.equals(response.getStatusCode())) {
				ObjectMapper objectMapper = new ObjectMapper();
				weatherDetails = objectMapper.readValue(response.getBody(), weatherDetails.getClass());
			}
		} catch (IOException e) {
			log.error("Exception", e);
		}
		return weatherDetails;
	}

}
