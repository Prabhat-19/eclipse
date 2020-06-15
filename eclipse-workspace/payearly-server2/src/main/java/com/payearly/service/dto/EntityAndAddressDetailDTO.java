package com.payearly.service.dto;

import java.util.List;

import com.payearly.domain.AdditionalDetail;
import com.payearly.domain.Address;
import com.payearly.domain.BankDetail;
import com.payearly.domain.EntityDetail;
import com.payearly.domain.EntityDocuments;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntityAndAddressDetailDTO {

    private EntityDetail details;

    private List<Address> addresses;

    private List<AdditionalDetail> additionalDetails;

    private List<EntityDocuments> entityDocuments;

    private List<BankDetail> bankDetails;

}
