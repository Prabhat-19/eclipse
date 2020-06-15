package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.payearly.domain.AdditionalDetail;

public interface AdditionalDetailService {

    List<AdditionalDetail> createMasterScoreChild(@Valid  List<AdditionalDetail> additionalDetail);

    AdditionalDetail updateScoreCardChild(@Valid AdditionalDetail additionalDetail);

    Page<AdditionalDetail> getAllScoreCardMaster(Pageable pageable);

    AdditionalDetail getAdditionalDetail(UUID id);

}
