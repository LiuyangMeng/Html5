package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SecondFilter implements Filter {
	private FilterConfig config;

	@Override
	public void destroy() {
		System.out.println("secodeFilter is destroy");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		String myname=config.getInitParameter("myname");
		System.out.println("secodeFilter come in :"+myname);
		arg2.doFilter(arg0, arg1);
		System.out.println("secodeFilter out of :"+myname);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config=arg0;
		System.out.println("secodeFilter init");
	}

}
