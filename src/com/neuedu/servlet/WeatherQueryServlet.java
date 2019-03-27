package com.neuedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.Weather;
import com.neuedu.service.WeatherService;
import com.neuedu.service.impl.WeatherServiceImpl;
import com.neuedu.vo.WeatherPage;

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
		String index=request.getParameter("index");
		String prepage=request.getParameter("prepage");
		String nextpage=request.getParameter("nextpage");
		WeatherPage a=WeatherPage.getInstance();
		if(index!=null) {
			a.setCurrentPage(0);
		}
		if(prepage!=null) {
			System.out.println("上一页");
			if(a.getCurrentPage()>=20)
				a.addPage(-20);
			System.out.println(a.getCurrentPage());
			System.out.println(a);
		}
		if(nextpage!=null) {
			System.out.println("下一页");
			a.addPage(20);
			System.out.println(a.getCurrentPage());
			System.out.println(a);
		}

		if (city == null)
			city = "";
		if (province == null)
			province = "";

		WeatherService weatherService = new WeatherServiceImpl();

		List<Weather> list = weatherService.getWeatherList(province, city,a.getCurrentPage());
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
