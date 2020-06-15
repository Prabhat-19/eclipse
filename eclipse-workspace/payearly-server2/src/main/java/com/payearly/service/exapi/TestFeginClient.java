package com.payearly.service.exapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.payearly.config.FeignConfiguration;
import com.payearly.domain.User;


@FeignClient(value = "jsonplaceholder", url = UrlContants.JBPM_BASE_URL, configuration = FeignConfiguration.class)
public interface TestFeginClient {

    @GetMapping("/{integer}")
    ResponseEntity<String> getUser(@PathVariable Integer integer);


    @GetMapping("/user/{userId}")
    User getPostById(@PathVariable("userId") Long postId);

    
} 