package com.uppcl.dashboard.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleCorsFilter implements Filter 
{

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCorsFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Initializing CORSFilter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requestToUse = (HttpServletRequest) request;
		HttpServletResponse responseToUse = (HttpServletResponse) response;

		responseToUse.setHeader("Access-Control-Allow-Origin",requestToUse.getHeader("Origin"));
		responseToUse.setHeader("Access-Control-Allow-Credentials", "true");
		responseToUse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
		responseToUse.setHeader("Access-Control-Max-Age", "3600");
		responseToUse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		chain.doFilter(requestToUse, responseToUse);
	}

	@Override
	public void destroy() {
		
	}
	

}
