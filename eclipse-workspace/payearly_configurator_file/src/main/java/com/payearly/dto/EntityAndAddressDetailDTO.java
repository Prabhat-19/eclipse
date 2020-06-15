package com.payearly.dto;

import java.util.List;

import com.payearly.model.EntityAddress;
import com.payearly.model.EntityDetails;

public class EntityAndAddressDetailDTO {

	private EntityDetails details;
	
	private List<EntityAddress> addresses;

	public EntityDetails getDetails() {
		return details;
	}

	public void setDetails(EntityDetails details) {
		this.details = details;
	}

	public List<EntityAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<EntityAddress> addresses) {
		this.addresses = addresses;
	};
	
	
}
