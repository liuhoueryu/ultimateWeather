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
import com.neuedu.entity.Everyday;
import com.neuedu.entity.Product;
import com.neuedu.util.DBManager;
import com.neuedu.vo.WeatherPage;

/**
 * Servlet implementation class Bar
 */
public class Bar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("echart_test.jsp").forward(request, response);
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
		
		List<Everyday> list1 = new ArrayList<Everyday>();
		DBManager dbManager = DBManager.getInstance();
		String sql ="select PM10,date from weather where city = '太原' order by date asc";
		sql += " limit ?, 10";
		ResultSet rs = dbManager.execQuery(sql,a.getCurrentPage());
		try {
			while (rs.next()) {
				list1.add(new Everyday(rs.getString(2),rs.getInt(1))); 
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
