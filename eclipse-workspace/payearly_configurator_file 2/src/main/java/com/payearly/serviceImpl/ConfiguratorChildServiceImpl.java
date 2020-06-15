package com.payearly.serviceImpl;

import org.springframework.stereotype.Service;

import com.payearly.model.ConfiguratorChild;
import com.payearly.model.ConfiguratorLookUpDetail;
import com.payearly.repository.ConfiguratorChildRepository;
import com.payearly.service.ConfiguratorChildService;

@Service
public class ConfiguratorChildServiceImpl implements  ConfiguratorChildService{

	private final ConfiguratorChildRepository configuratorChildRepository;

	public ConfiguratorChildServiceImpl(ConfiguratorChildRepository configuratorChildRepository) {
		super();
		this.configuratorChildRepository = configuratorChildRepository;
	}


	@Override
	public ConfiguratorChild save(ConfiguratorChild configuratorChild) {
		// TODO Auto-generated method stub
		return configuratorChildRepository.save(configuratorChild);
	}


}
