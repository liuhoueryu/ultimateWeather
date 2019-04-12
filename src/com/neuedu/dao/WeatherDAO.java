package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.CityWeather;
import com.neuedu.entity.Weather;

public interface WeatherDAO {
	List<Weather> findWeatherList(String province,String city,int page);
}
