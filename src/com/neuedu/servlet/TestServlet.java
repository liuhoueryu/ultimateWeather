package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public TestServlet() {
    	System.out.println("构造方法被调用。。。");
    }
	
    @Override
    public void init() throws ServletException {
    	System.out.println("init()被调用。。。");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	super.service(req, resp);
    	System.out.println("service()被调用。。。");
    }
    
    @Override
    public void destroy() {
    	System.out.println("destroy()被调用。。。");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()被调用。。。");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()被调用。。。");
	}

}
