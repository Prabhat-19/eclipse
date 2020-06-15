package com.payearly.service.dto;

import java.util.List;
import java.util.UUID;

import com.payearly.domain.ConfiguratorLookUpDetail;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfiguratoreLookUpDTO {

    private UUID id;

    private String name;

    private String discription;

    private List<ConfiguratorLookUpDetailDTO> configuratorLookUpDetail;
}
