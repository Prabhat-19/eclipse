package com.payearly.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import feign.RetryableException;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;

//@Configuration
@EnableFeignClients(basePackages = "com.payearly")
@Import(FeignClientsConfiguration.class)
public class FeignConfiguration {

    /**
     * Set the Feign specific log level to log client REST requests.
     */
	
    @Bean
    feign.Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.BASIC;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
         return new BasicAuthRequestInterceptor("PEMaker", "Welcome1");
    }

}
