package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.WeatherDAO;
import com.neuedu.entity.Weather;
import com.neuedu.util.DBManager;

public class WeatherDAOImpl implements WeatherDAO {

	@Override
	public List<Weather> findWeatherList(String province, String city,int page) {
		DBManager dbManager = DBManager.getInstance();
		String sql = "select * from weather where city like ? and province like ? order by province asc, city asc,date desc";
		sql += " limit ?, 20";
		ResultSet rs = dbManager.execQuery(sql, "%" + city + "%", "%" + province + "%",page);
		List<Weather> list = new ArrayList<>();

		try {
			while (rs.next()) {
				Weather weather = new Weather();
				weather.setProvince(rs.getString(1));
				weather.setCity(rs.getString(2));
				weather.setWeather(rs.getString(3));
				weather.setTemperature(rs.getString(4));
				weather.setRainfall(rs.getString(5));
				weather.setDate(rs.getString(6));

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
