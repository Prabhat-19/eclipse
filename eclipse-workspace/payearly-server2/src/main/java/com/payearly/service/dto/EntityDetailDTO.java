package com.payearly.service.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityDetailDTO {

    private UUID id;

    private String constitution;

    private String entityName;

    private String entityCin;

    private String entityPan;

    private String entityWebsite;

    private String gstin;

    private String entityTin;

    private String natureOfBusiness;

    private Integer flag;

    private String location;
}
