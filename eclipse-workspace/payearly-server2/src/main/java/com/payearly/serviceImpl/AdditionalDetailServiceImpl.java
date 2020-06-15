package com.payearly.serviceImpl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.AdditionalDetail;
import com.payearly.repository.AdditionalDetailRepository;
import com.payearly.service.AdditionalDetailService;


@Service
@Transactional
public class AdditionalDetailServiceImpl implements AdditionalDetailService{

    private final AdditionalDetailRepository additionalDetailRepository;
    
    public AdditionalDetailServiceImpl(AdditionalDetailRepository additionalDetailRepository) {
        super();
        this.additionalDetailRepository = additionalDetailRepository;
    }

    @Override
    public  List<AdditionalDetail> createMasterScoreChild(@Valid  List<AdditionalDetail> additionalDetail) {

        return additionalDetailRepository.saveAll(additionalDetail);
    }

    @Override
    public AdditionalDetail updateScoreCardChild(@Valid AdditionalDetail additionalDetail) {

        return additionalDetailRepository.save(additionalDetail);
    }

    @Override
    public Page<AdditionalDetail> getAllScoreCardMaster(Pageable pageable) {

        return additionalDetailRepository.findAll(pageable);
    }

    @Override
    public AdditionalDetail getAdditionalDetail(UUID id) {

        return additionalDetailRepository.findById(id).get();
    }

}
