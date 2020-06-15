package com.payearly.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.ConfiguratorChild;
import com.payearly.enums.ScoreCardType;
import com.payearly.repository.ConfiguratorChildRepository;
import com.payearly.service.ConfiguratorChildService;


@Service
@Transactional
public class ConfiguratorChildServiceImpl implements ConfiguratorChildService {

    private final ConfiguratorChildRepository configuratorChildRepository;

    public ConfiguratorChildServiceImpl(ConfiguratorChildRepository configuratorChildRepository) {
        super();
        this.configuratorChildRepository = configuratorChildRepository;
    }

    @Override
    public ConfiguratorChild save(ConfiguratorChild configuratorChild) {
    	configuratorChild.setConfiguratorType(ScoreCardType.PAYEARLY);
        return configuratorChildRepository.save(configuratorChild);
    }

	@Override
	public ConfiguratorChild saveBankConfiguration(ConfiguratorChild configuratorChild) {
		configuratorChild.setConfiguratorType(ScoreCardType.BANK);
		
        return configuratorChildRepository.save(configuratorChild);
	}

}
