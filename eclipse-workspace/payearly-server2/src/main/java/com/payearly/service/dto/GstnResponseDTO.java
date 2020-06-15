package com.payearly.service.dto;

import java.util.List;

import com.payearly.domain.Address;
import com.payearly.domain.EntityDetail;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GstnResponseDTO {

    private EntityDetail entityDetail;

    private List<Address> entityOperatingAddress;

    private String  processId;
    
    private Integer processInstanceId;

    private Integer parentInstanceId;
    
    private Integer statusCode;
    
}