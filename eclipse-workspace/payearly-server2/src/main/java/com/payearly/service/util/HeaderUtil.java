package com.payearly.service.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {

    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-payearly-alert", message);
        headers.add("X-payearly-params", param);

        return headers;
    }

    public static HttpHeaders createAlerts(String message, List<String> param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-payearly-alert", message);
        headers.add("X-payearly-params", param.toString());
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {

        return createAlert("A new " + entityName + " is created with identifier " + param, param);
    }

    public static HttpHeaders createEntitiesCreationAlert(String entityName, List<String> param) {

        return createAlerts("A List of" + entityName + " is created with identifier " + param, param);
    }

    public static HttpHeaders createEntityUpdateAlerts(String entityName, List<String> param) {

        return createAlerts("A List of" + entityName + " is created with identifier " + param, param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {

        return createAlert("A " + entityName + " is updated with identifier " + param, param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {

        return createAlert("A " + entityName + " is deleted with identifier " + param, param);
    }

}
