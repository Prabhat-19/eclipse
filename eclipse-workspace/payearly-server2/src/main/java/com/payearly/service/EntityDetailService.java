package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.payearly.domain.BankDetail;
import com.payearly.domain.EntityDetail;
import com.payearly.domain.EntityMapping;
import com.payearly.service.dto.EntityAndAddressDetailDTO;
import com.payearly.service.dto.EntityDetailDTO;
import com.payearly.service.dto.EntityMappingDTO;
import com.payearly.service.dto.EntityNameDto;

public interface EntityDetailService {
    
    EntityAndAddressDetailDTO getEntityDetail(String id);
    
    EntityDetail getEntityDetail(EntityAndAddressDetailDTO entityDetails);
    
    void deleteEntityDetail(UUID gstnId);
    
    EntityMapping createEntityDetail(EntityMappingDTO entityAndAddressDetailDTO);
    
    List<EntityMapping> getEntityMapping(String id);

    List<EntityNameDto> getAllEntity();

    List<EntityDetailDTO> getAllEntityAndAddress();

    BankDetail createBankDetail(@Valid BankDetail bankDetail);

    String uploadDocument(String label, @Valid MultipartFile file);

    EntityDetail createEntityDetail(@Valid EntityDetail entityDetail);

    EntityDetail updateEntityDetail(@Valid EntityDetail entityDetail);

    Page<EntityDetail> getEntityDetail(Pageable pageable);

    EntityDetail getEntityDetails(UUID id);

    EntityAndAddressDetailDTO getEntityDetail(UUID entityDetailId);

}
