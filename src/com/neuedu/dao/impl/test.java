package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.WeatherDAO;
import com.neuedu.entity.CityWeather;
import com.neuedu.entity.Weather;
import com.neuedu.util.DBManager;

public class test {

	public static void main(String[] args) {
		
		List<CityWeather> list = new ArrayList<CityWeather>();
		DBManager dbManager = DBManager.getInstance();
		String sql ="select PM10,date from weather where city = '̫ԭ' order by date asc limit 0, 10";
		ResultSet rs = dbManager.execQuery(sql);
		try {
			while (rs.next()) {
				CityWeather weather = new CityWeather();
				weather.setPM(rs.getInt(1));
				weather.setDate(rs.getString(2));

				list.add(weather);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (CityWeather cityWeather : list) {
			System.out.println(cityWeather.getPM());
		}
		 
	}
}
