package com.payearly.service.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfiguratorLookUpDetailDTO {

	private UUID id;

	private String categoryType;

	private Float value;

	private String discription;
}
