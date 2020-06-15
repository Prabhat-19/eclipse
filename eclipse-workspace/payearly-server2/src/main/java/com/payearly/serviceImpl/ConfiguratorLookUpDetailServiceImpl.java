package com.payearly.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.ConfiguratorLookUpDetail;
import com.payearly.repository.ConfiguratorLookUpDetailRepository;
import com.payearly.service.ConfiguratorLookUpDetailService;


@Service
@Transactional
public class ConfiguratorLookUpDetailServiceImpl implements ConfiguratorLookUpDetailService {

    private final ConfiguratorLookUpDetailRepository configuratorLookUpDetailRepository;

    public ConfiguratorLookUpDetailServiceImpl(ConfiguratorLookUpDetailRepository configuratorLookUpDetailRepository) {
        super();
        this.configuratorLookUpDetailRepository = configuratorLookUpDetailRepository;
    }

    @Override
    public ConfiguratorLookUpDetail save(ConfiguratorLookUpDetail configuratorLookUpDetail) {

        return configuratorLookUpDetailRepository.save(configuratorLookUpDetail);
    }

    @Override
    public List<ConfiguratorLookUpDetail> getDiscountingNatureCategury(UUID discountingNatureId) {

        return configuratorLookUpDetailRepository.findByconfiguratorLookUp_id(discountingNatureId);
    }

	@Override
	public List<ConfiguratorLookUpDetail> save(List<ConfiguratorLookUpDetail> configuratorLookUpDetail) {

		return configuratorLookUpDetailRepository.saveAll(configuratorLookUpDetail);
	}

}
