package com.payearly.service;

import java.util.List;
import java.util.UUID;

import com.payearly.domain.ConfiguratorLookUpDetail;

public interface ConfiguratorLookUpDetailService {

	ConfiguratorLookUpDetail save(ConfiguratorLookUpDetail configuratorLookUpDetail);

	List<ConfiguratorLookUpDetail> getDiscountingNatureCategury(UUID discountingNatureId);

	List<ConfiguratorLookUpDetail>  save(List<ConfiguratorLookUpDetail> configuratorLookUpDetail);

}
