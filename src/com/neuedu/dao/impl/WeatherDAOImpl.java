package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.WeatherDAO;
import com.neuedu.entity.CityWeather;
import com.neuedu.entity.Weather;
import com.neuedu.util.DBManager;

public class WeatherDAOImpl implements WeatherDAO {
	@Override
	public List<CityWeather> findCityWeatherList() {
		DBManager dbManager = DBManager.getInstance();
		String sql ="select PM10,date from weather where city = '̫ԭ' order by date asc limit 0, 10";
		ResultSet rs = dbManager.execQuery(sql);
		List<CityWeather> list = new ArrayList<>();
		try {
			while (rs.next()) {
				CityWeather weather = new CityWeather();
				weather.setPM(rs.getInt(1));
				weather.setDate(rs.getString(2));

				list.add(weather);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}
		return null;
	}

	@Override
	public List<Weather> findWeatherList(String province, String city, int page) {
		DBManager dbManager = DBManager.getInstance();
		String sql = "select * from weather where city like ? and province like ? order by province asc, city asc,date desc";
		sql += " limit ?, 20";
		ResultSet rs = dbManager.execQuery(sql, "%" + city + "%", "%" + province + "%", page);
		List<Weather> list = new ArrayList<>();

		try {
			while (rs.next()) {
				Weather weather = new Weather();
				weather.setProvince(rs.getString(2));
				weather.setCity(rs.getString(3));
				weather.setWeather(rs.getString(4));
				weather.setTemperature(rs.getString(5));
				weather.setRainfall(rs.getString(6));
				weather.setDate(rs.getString(7));
				weather.setAqi(rs.getString(8));
				weather.setPm25(rs.getString(9));
				weather.setPm10(rs.getString(10));
				weather.setCo(rs.getString(11));
				weather.setNo2(rs.getString(12));
				weather.setO3(rs.getString(13));
				weather.setSo2(rs.getString(14));

				list.add(weather);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}
		return null;
	}

}
