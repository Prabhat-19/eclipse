package com.payearly.service.exapi;
import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "taxprogsp", url = UrlContants.GSTN_URL)
public interface GstnApiClientService {


     @GetMapping
     ResponseEntity<JSONObject>  getGstenApiResponse(@RequestParam(value="Gstin") String gstin);

}
