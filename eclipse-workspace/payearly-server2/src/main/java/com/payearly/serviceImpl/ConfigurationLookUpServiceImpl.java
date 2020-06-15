package com.payearly.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.payearly.domain.ConfiguratorLookUp;
import com.payearly.repository.ConfiguratorLookUpRepository;
import com.payearly.service.ConfiguratorLookUpService;
import com.payearly.service.dto.LookUpDTO;

@Service
@Transactional
public class ConfigurationLookUpServiceImpl implements ConfiguratorLookUpService {

    private final ConfiguratorLookUpRepository configuratorLookUpRepository;

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

    @Override
    public List<LookUpDTO> getDiscountingNatureCategury() {

        return configuratorLookUpRepository.findAll().stream().map(lDto -> {
            LookUpDTO lookUpDTO = new LookUpDTO();
            lookUpDTO.setId(lDto.getId());
            lookUpDTO.setName(lDto.getName());
            lookUpDTO.setDiscription(lDto.getDiscription());

            return lookUpDTO;
        }).collect(Collectors.toList());
    }

}
