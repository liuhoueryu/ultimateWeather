package com.neuedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.NewsType;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;

public class NewsInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {
		
		//读取新闻类型的列表，保存在application属性范围中
		
	    //获取application对象
		ServletContext application = this.getServletContext();
		
		NewsService newsService = new NewsServiceImpl();
		
		List<NewsType> list = newsService.getNewsTypeList();
		
		application.setAttribute("list", list);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
