package com.neuedu.service.impl;

import java.util.List;

import com.neuedu.dao.WeatherDAO;
import com.neuedu.dao.impl.WeatherDAOImpl;
import com.neuedu.entity.Weather;
import com.neuedu.service.WeatherService;

public class WeatherServiceImpl implements WeatherService {
	private WeatherDAO weatherDAO = new WeatherDAOImpl();

	@Override
	public List<Weather> getWeatherList(String province, String city) {
		return weatherDAO.findWeatherList(province, city);
	}

}
