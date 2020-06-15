package com.payearly.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.ConfiguratorLookUp;
import com.payearly.service.ConfiguratorLookUpService;
import com.payearly.service.dto.LookUpDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class ConfiguratorLookUpRresource {

    private final ConfiguratorLookUpService lookUpService;

    public ConfiguratorLookUpRresource(ConfiguratorLookUpService lookUpService) {
        super();
        this.lookUpService = lookUpService;
    }

    /**
     * Saving data into configurator_look_up table
     */
    @PostMapping("/configurator-lookup")
    public ConfiguratorLookUp save(@RequestBody ConfiguratorLookUp configuratorLookUp) {

        return lookUpService.save(configuratorLookUp);
    }

    /**
     * Getting type of discount information from configurator_look_up table
     */
    @GetMapping("/configurator-lookup/{discountingNature}")
    public ConfiguratorLookUp getDiscountingNatureCategory(@PathVariable String discountingNature) {

        return lookUpService.getDiscountingNatureCategury(discountingNature);
    }

    /**
     * Getting all data from configurator_look_up table
     */
    @GetMapping("/configurator-lookup")
    public List<LookUpDTO> getAllLookUp() {

        return lookUpService.getDiscountingNatureCategury();

    }
}
