package com.payearly.web.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.model.ConfiguratorLookUp;
import com.payearly.model.ConfiguratorLookUpDetail;
import com.payearly.service.ConfiguratorLookUpService;

@RestController
@RequestMapping("/api")
public class ConfiguratorLookUpRresource {

	private final ConfiguratorLookUpService lookUpService;

	public ConfiguratorLookUpRresource(ConfiguratorLookUpService lookUpService) {
		super();
		this.lookUpService = lookUpService;
	}

	@PostMapping("/configurator-lookup")
	public ConfiguratorLookUp save(@RequestBody ConfiguratorLookUp configuratorLookUp) {

		return lookUpService.save(configuratorLookUp);

	}
	
	@GetMapping("/configurator-lookup/{discountingNature}")
	public ConfiguratorLookUp getDiscountingNatureCategory(@PathVariable String discountingNature){
		
		return lookUpService.getDiscountingNatureCategury(discountingNature);
		
	}
}
