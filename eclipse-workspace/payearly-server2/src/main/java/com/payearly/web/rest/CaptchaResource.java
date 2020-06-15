package com.payearly.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.captcha.botdetect.web.servlet.SimpleCaptcha;
import com.payearly.config.Constants;
import com.payearly.domain.BasicValidationResult;
import com.payearly.domain.CaptchaInput;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class CaptchaResource {

	@PostMapping("/basic-captcha")
	public BasicValidationResult getCaptcha(@Valid @RequestBody CaptchaInput captcha, HttpServletRequest request){
		
		 	SimpleCaptcha simpleCaptcha = SimpleCaptcha.load(request);
	        boolean isHuman = simpleCaptcha.validate(captcha.getUserEnteredCaptchaCode(), captcha.getCaptchaId());
	      
	        BasicValidationResult validationResult = new BasicValidationResult();
	        
	        if (isHuman == false) {
	            validationResult.setSuccess(false);
	        } else {
	            validationResult.setSuccess(true);
	        }
	 
		return validationResult;
	}
}
