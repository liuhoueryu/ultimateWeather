package com.neuedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.CitySet;

/**
 * Servlet implementation class CityChoose
 */
public class CityChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String charSet = this.getServletContext().getInitParameter("charSet");
		request.setCharacterEncoding(charSet);
		String city = request.getParameter("city");
		CitySet c=CitySet.getInstance();
		c.setCity(city);
		System.out.println(c.getCity());
		request.getRequestDispatcher("echart_test.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}