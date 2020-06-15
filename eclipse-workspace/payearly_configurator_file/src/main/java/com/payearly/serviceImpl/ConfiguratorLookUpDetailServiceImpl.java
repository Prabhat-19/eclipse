package com.payearly.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.payearly.model.ConfiguratorLookUpDetail;
import com.payearly.repository.ConfiguratorLookUpDetailRepository;
import com.payearly.service.ConfiguratorLookUpDetailService;

@Service
public class ConfiguratorLookUpDetailServiceImpl implements ConfiguratorLookUpDetailService {

    private final ConfiguratorLookUpDetailRepository  configuratorLookUpDetailRepository;

	public ConfiguratorLookUpDetailServiceImpl(ConfiguratorLookUpDetailRepository configuratorLookUpDetailRepository) {
		super();
		this.configuratorLookUpDetailRepository = configuratorLookUpDetailRepository;
	}

	@Override
	public ConfiguratorLookUpDetail save(ConfiguratorLookUpDetail configuratorLookUpDetail) {
		// TODO Auto-generated method stub
		return configuratorLookUpDetailRepository.save(configuratorLookUpDetail);
	}

	@Override
	public List<ConfiguratorLookUpDetail> getDiscountingNatureCategury(UUID discountingNatureId) {
		// TODO Auto-generated method stub
		return configuratorLookUpDetailRepository.findByconfiguratorLookUp_id(discountingNatureId);
	}

}
