package com.payearly.service;

import com.payearly.model.ConfiguratorLookUp;
import com.payearly.model.ConfiguratorLookUpDetail;

public interface ConfiguratorLookUpService {

	ConfiguratorLookUp save(ConfiguratorLookUp configuratorLookUp);

	ConfiguratorLookUp getDiscountingNatureCategury(String discountingNature);

}
