package com.payearly.service.dto;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntityCreationDTO {

    private String entityName;

    @NotNull
    @Size(min = 15, max = 15)
    private String gstin;

    private String location;
    
    private String entity;

	
}
