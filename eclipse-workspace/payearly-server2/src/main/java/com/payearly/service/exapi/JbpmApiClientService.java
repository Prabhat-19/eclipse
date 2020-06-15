package com.payearly.service.exapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.payearly.config.FeignConfiguration;
import com.payearly.service.dto.GstnResponseDTO;

@FeignClient(name = "kie-server", url = UrlContants.JBPM_BASE_URL, configuration = FeignConfiguration.class)
public interface JbpmApiClientService {

    @PostMapping("/processes/Payearly_11_Oct.Payearly/instances/")
    ResponseEntity<String> jbpmStartApi(@RequestBody GstnResponseDTO gstnResponseDTO);

    @GetMapping("/processes/instances/{processInstanceId}")
    ResponseEntity<String> getJbpmTaskInstance(@PathVariable(value = "processInstanceId") Integer processInstanceId);
    
    @PutMapping("/tasks/{taskId}/states/started")
    ResponseEntity<Void> startJbpmTaskOne(@PathVariable(value = "taskId") Integer taskId);

    @PutMapping("/tasks/{taskId}/states/completed")
    ResponseEntity<Void> compleatationOfTaskOne(@PathVariable(value = "taskId") Integer taskId, @RequestBody GstnResponseDTO gstnResponseDTO);
    
    @GetMapping("/tasks/{taskId}/contents/output")
    ResponseEntity<String> taskOneOutput(@PathVariable(value = "taskId") Integer taskId);
   

}
