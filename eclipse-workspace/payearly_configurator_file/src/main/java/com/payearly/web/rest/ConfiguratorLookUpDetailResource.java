package com.payearly.web.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.model.ConfiguratorLookUpDetail;
import com.payearly.service.ConfiguratorLookUpDetailService;

@RestController
@RequestMapping("/api")
public class ConfiguratorLookUpDetailResource {

	private final ConfiguratorLookUpDetailService  configuratorLookUpDetailService;
	
	public ConfiguratorLookUpDetailResource(ConfiguratorLookUpDetailService configuratorLookUpDetailService) {
		super();
		this.configuratorLookUpDetailService = configuratorLookUpDetailService;
	}

	@PostMapping("/configurator-lookup-detail")
	public ConfiguratorLookUpDetail crreateConfiguratorLookUpDetail(@RequestBody ConfiguratorLookUpDetail configuratorLookUpDetail){
		
		return configuratorLookUpDetailService.save(configuratorLookUpDetail);
	}
	
	@GetMapping("/configurator-lookup-detail/{discountingNatureId}")
	List<ConfiguratorLookUpDetail> getDiscountingNatureCategory(@PathVariable UUID discountingNatureId){
		
		return configuratorLookUpDetailService.getDiscountingNatureCategury(discountingNatureId);
	}
	
}
