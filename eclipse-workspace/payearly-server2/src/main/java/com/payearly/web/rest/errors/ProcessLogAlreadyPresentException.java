package com.payearly.web.rest.errors;


public class ProcessLogAlreadyPresentException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public ProcessLogAlreadyPresentException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "processLog  already present!", "processLog", "processLogexist");
    }
}
