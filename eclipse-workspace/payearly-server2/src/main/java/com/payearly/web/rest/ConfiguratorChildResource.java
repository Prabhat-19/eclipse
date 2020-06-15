package com.payearly.web.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.ConfiguratorChild;
import com.payearly.service.ConfiguratorChildService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class ConfiguratorChildResource {

    private final ConfiguratorChildService configuratorChildService;

    public ConfiguratorChildResource(ConfiguratorChildService configuratorChildService) {
        super();
        this.configuratorChildService = configuratorChildService;
    }

    /**
     * Saving data into configurator_child table
     * 
     * @param configuratorChild of type ConfiguratorChild class
     */
    @PostMapping("/configurator-child")
    public ConfiguratorChild createConfiguratorChild(@RequestBody ConfiguratorChild configuratorChild) {

        return configuratorChildService.save(configuratorChild);

    }

    /**
     * Saving data into configurator_child table
     * 
     * @param configuratorChild of type ConfiguratorChild class
     */
    @PostMapping("/bank-configurator-child")
    public ConfiguratorChild createBankConfiguratorChild(@RequestBody ConfiguratorChild configuratorChild) {

        return configuratorChildService.saveBankConfiguration(configuratorChild);
      }
}
