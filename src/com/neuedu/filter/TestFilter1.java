package com.neuedu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestFilter1 implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//预处理
		System.out.println("Before TestFilter1");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		//后处理
		System.out.println("After TestFilter1");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
