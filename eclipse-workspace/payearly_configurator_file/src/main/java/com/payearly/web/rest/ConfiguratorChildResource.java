package com.payearly.web.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.model.ConfiguratorChild;
import com.payearly.service.ConfiguratorChildService;

@RestController
public class ConfiguratorChildResource {

	private final ConfiguratorChildService configuratorChildService;
	
	public ConfiguratorChildResource(ConfiguratorChildService configuratorChildService) {
		super();
		this.configuratorChildService = configuratorChildService;
	}

	@PostMapping("/configurator-child")
	public ConfiguratorChild createConfiguratorChild(@RequestBody ConfiguratorChild configuratorChild){
		
		return configuratorChildService.save(configuratorChild);
		
	}
	
	
}
