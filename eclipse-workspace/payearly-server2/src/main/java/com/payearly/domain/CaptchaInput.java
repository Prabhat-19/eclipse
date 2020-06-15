package com.payearly.domain;

public class CaptchaInput {

	private String userEnteredCaptchaCode;
	private String captchaId;
	
	public String getUserEnteredCaptchaCode() {
		return userEnteredCaptchaCode;
	}
	public void setUserEnteredCaptchaCode(String userEnteredCaptchaCode) {
		this.userEnteredCaptchaCode = userEnteredCaptchaCode;
	}
	public String getCaptchaId() {
		return captchaId;
	}
	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}

}