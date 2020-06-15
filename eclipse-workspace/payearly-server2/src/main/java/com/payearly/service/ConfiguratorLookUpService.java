package com.payearly.service;

import java.util.List;

import com.payearly.domain.ConfiguratorLookUp;
import com.payearly.service.dto.LookUpDTO;

public interface ConfiguratorLookUpService {

	ConfiguratorLookUp save(ConfiguratorLookUp configuratorLookUp);

	ConfiguratorLookUp getDiscountingNatureCategury(String discountingNature);

	List<LookUpDTO> getDiscountingNatureCategury();

}
