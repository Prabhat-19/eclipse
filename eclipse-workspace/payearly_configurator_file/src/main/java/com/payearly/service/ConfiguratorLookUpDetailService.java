package com.payearly.service;

import java.util.List;
import java.util.UUID;

import com.payearly.model.ConfiguratorLookUpDetail;

public interface ConfiguratorLookUpDetailService {

	ConfiguratorLookUpDetail save(ConfiguratorLookUpDetail configuratorLookUpDetail);

	List<ConfiguratorLookUpDetail> getDiscountingNatureCategury(UUID discountingNatureId);

}
