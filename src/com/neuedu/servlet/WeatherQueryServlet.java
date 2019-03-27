package com.neuedu.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.User;
import com.neuedu.entity.Weather;
import com.neuedu.service.UserService;
import com.neuedu.service.WeatherService;
import com.neuedu.service.impl.UserServiceImpl;
import com.neuedu.service.impl.WeatherServiceImpl;
import com.neuedu.util.DBManager;

public class WeatherQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String charSet = this.getServletContext().getInitParameter("charSet");
		request.setCharacterEncoding(charSet);

		// 转码
		// request.setCharacterEncoding("utf-8");
		// 接收数据
		String province = request.getParameter("province");
		String city = request.getParameter("city");

		if (city == null)
			city = "";
		if (province == null)
			province = "";

		WeatherService weatherService = new WeatherServiceImpl();

		List<Weather> list = weatherService.getWeatherList(province, city);
		request.setAttribute("list", list);
		request.setAttribute("province", province);
		request.setAttribute("city", city);
		request.getRequestDispatcher("weather_query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
