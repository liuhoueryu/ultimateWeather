package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.Weather;

public interface WeatherService {
	List<Weather> getWeatherList(String province,String city,int page);
}
