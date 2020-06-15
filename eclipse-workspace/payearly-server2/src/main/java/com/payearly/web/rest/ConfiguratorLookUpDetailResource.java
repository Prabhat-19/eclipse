package com.payearly.web.rest;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.ConfiguratorLookUpDetail;
import com.payearly.domain.EntityCategory;
import com.payearly.repository.EntityCategoryRepository;
import com.payearly.service.ConfiguratorLookUpDetailService;
import com.payearly.web.rest.errors.BadRequestAlertException;

import io.micrometer.core.lang.NonNull;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class ConfiguratorLookUpDetailResource {

	 private static final String ENTITY_NAME = "scoreCardChild";
	 
    private final ConfiguratorLookUpDetailService configuratorLookUpDetailService;
    private final EntityCategoryRepository categoryRepository;

    public ConfiguratorLookUpDetailResource(ConfiguratorLookUpDetailService configuratorLookUpDetailService,
			EntityCategoryRepository categoryRepository) {
		super();
		this.configuratorLookUpDetailService = configuratorLookUpDetailService;
		this.categoryRepository = categoryRepository;
	}

	/**
     * Saving data into configurator_look_up_detail table
     * 
     * @param ConfiguratorLookUpDetail Object
     * @return ConfiguratorLookUpDetail Object
     */
    @PostMapping("/configurator-lookup-detail")
    public ConfiguratorLookUpDetail crreateConfiguratorLookUpDetail(@RequestBody ConfiguratorLookUpDetail configuratorLookUpDetail) {
     if (Objects.nonNull(configuratorLookUpDetail.getEntityCategory())) {
    	  
		if ( categoryRepository.findBycategory(configuratorLookUpDetail.getEntityCategory().getCategory().trim()).isPresent()) {
			
			throw  new BadRequestAlertException("entity category name must be uique", "entityCategory", configuratorLookUpDetail.getEntityCategory().getCategory().trim());
		}
	}
        return configuratorLookUpDetailService.save(configuratorLookUpDetail);
    }

    /**
     * Saving data into configurator_look_up_details table
     * 
     * @param ConfiguratorLookUpDetail Object
     * @return ConfiguratorLookUpDetail Object
     */
    @PostMapping("/configurator-lookup-details")
    public List<ConfiguratorLookUpDetail>  crreateConfiguratorLookUpDetails(@RequestBody List<ConfiguratorLookUpDetail> configuratorLookUpDetail) {

        return configuratorLookUpDetailService.save(configuratorLookUpDetail);
    }

    /**
     * Getting values corresponding to UUID from configurator_look_up_detail
     * table
     * 
     * @param UUID
     */
    @GetMapping("/configurator-lookup-detail/{discountingNatureId}")
    public List<ConfiguratorLookUpDetail> getDiscountingNatureCategory(@PathVariable UUID discountingNatureId) {

        return configuratorLookUpDetailService.getDiscountingNatureCategury(discountingNatureId);
    }

}
