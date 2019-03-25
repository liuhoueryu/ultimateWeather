package com.neuedu.filter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogTimeFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//×ªÐÍ
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp  = (HttpServletResponse)response;
		
		long begin = System.currentTimeMillis();
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		
		String uri = req.getRequestURI();
		
		System.out.println("uri=" + uri + " time=" + (end-begin) + "ms");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
