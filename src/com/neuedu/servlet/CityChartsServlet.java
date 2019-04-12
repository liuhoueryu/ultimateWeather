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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.entity.CityWeather;
import com.neuedu.util.DBManager;
import com.neuedu.vo.WeatherPage;

/**
 * Servlet implementation class Bar
 */
public class CityChartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("echart_city.jsp").forward(request, response);
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String index=request.getParameter("index");
		String prepage=request.getParameter("prepage");
		String nextpage=request.getParameter("nextpage");
		WeatherPage a=WeatherPage.getInstance();
		if(index!=null) {
			a.setCurrentPage(0);
		}
		if(prepage!=null) {
			System.out.println("上一页");
			if(a.getCurrentPage()>=10)
				a.addPage(-10);
			System.out.println(a.getCurrentPage());
			System.out.println(a);
		}
		if(nextpage!=null) {
			System.out.println("下一页");
			a.addPage(10);
			System.out.println(a.getCurrentPage());
			System.out.println(a);
		}
		
		List<CityWeather> list1 = new ArrayList<CityWeather>();
		DBManager dbManager = DBManager.getInstance();
		String sql ="select DISTINCT city,aqi,temperature,pm10,`PM2.5` from weather WHERE date='2018-03-03'";
		sql += " limit ?, 10";
		ResultSet rs = dbManager.execQuery(sql,a.getCurrentPage());
		try {
			while (rs.next()) {
				list1.add(new CityWeather(rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5))); 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		ObjectMapper mapper1 = new ObjectMapper();
		String json1 = mapper1.writeValueAsString(list1);

		// 将json数据返回给客户端
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(json1); 
	}

}
