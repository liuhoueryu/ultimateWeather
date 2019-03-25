package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.News;
import com.neuedu.entity.NewsType;
import com.neuedu.entity.User;
import com.neuedu.service.NewsService;
import com.neuedu.service.impl.NewsServiceImpl;

public class NewsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象
		PrintWriter out = response.getWriter();		
		
		//获取session对象
	    HttpSession session = request.getSession();	
	    
	    
		//接收数据
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int typeid = Integer.parseInt(request.getParameter("typeid"));
		
		NewsService newsService = new NewsServiceImpl();
		
		News news = new News();
		
		news.setTitle(title);
		news.setContent(content);
		
		NewsType newsType = new NewsType();
		newsType.setTypeid(typeid);
		news.setNewsType(newsType);
		
		//从session中取出当前用户
		User user = (User)session.getAttribute("user");
		news.setUser(user);
		
		news.setPubtime(new Date());
		
		if(newsService.addNews(news)){
			out.print("<script>if(confirm('新闻添加成功,是否继续添加')){location='news_add.jsp'}else{location='news_query.jsp'}</script>");
		}else{
			out.print("<script>alert('新闻添加失败，请重新检查');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
