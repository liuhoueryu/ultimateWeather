package com.neuedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;
import com.neuedu.vo.NewsPage;

public class NewsQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = 10;
		
		String scurrentPage = request.getParameter("currentPage");
		
		if(scurrentPage==null){
			scurrentPage = "1";
		}
		
		int currentPage = Integer.parseInt(scurrentPage);
		
		NewsService newsService = new NewsServiceImpl();
		
		NewsPage newsPage = newsService.getNewsPage(currentPage, pageSize);
		
		request.setAttribute("newsPage", newsPage);
		
		request.getRequestDispatcher("news_query.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
