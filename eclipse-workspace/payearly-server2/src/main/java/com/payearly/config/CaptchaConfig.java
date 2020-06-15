package com.payearly.config;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.captcha.botdetect.web.servlet.SimpleCaptchaServlet;

@Configuration
public class CaptchaConfig {

	@Bean
	public ServletRegistrationBean<HttpServlet> captchaServlet(){
		
		ServletRegistrationBean<HttpServlet> servRegisterBean = new ServletRegistrationBean<HttpServlet>();
		servRegisterBean.setServlet(new SimpleCaptchaServlet());
		servRegisterBean.addUrlMappings("/api/simple-captcha-endpoint/*");
		servRegisterBean.setLoadOnStartup(1);
		return servRegisterBean;
	}
}
