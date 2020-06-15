package com.payearly.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.payearly.model.ConfiguratorLookUp;
import com.payearly.model.ConfiguratorLookUpDetail;
import com.payearly.repository.ConfiguratorLookUpRepository;
import com.payearly.service.ConfiguratorLookUpService;

@Service
@Transactional
public class ConfigurationLookUpServiceImpl implements ConfiguratorLookUpService {

    private  final ConfiguratorLookUpRepository configuratorLookUpRepository;
     
	public ConfigurationLookUpServiceImpl(ConfiguratorLookUpRepository configuratorLookUpRepository) {
		super();
		this.configuratorLookUpRepository = configuratorLookUpRepository;
	}

	@Override
	public ConfiguratorLookUp save(ConfiguratorLookUp configuratorLookUp) {

		return configuratorLookUpRepository.save(configuratorLookUp);
	}

	@Override
	public ConfiguratorLookUp getDiscountingNatureCategury(String discountingNature) {

		return configuratorLookUpRepository.findByname(discountingNature);
	}
	
}
