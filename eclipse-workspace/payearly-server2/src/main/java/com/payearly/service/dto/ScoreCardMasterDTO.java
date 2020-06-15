package com.payearly.service.dto;

import java.util.UUID;

import com.payearly.domain.ConfiguratorLookUp;
import com.payearly.enums.ScoreCardCategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreCardMasterDTO {

    private UUID id;

    private ScoreCardCategoryType ScoreCardCategoryType;

    private ConfiguratoreLookUpDTO criteriaLookUp;

    private String rejectionCriteria;
    
    private String weightage;

    private String documentProof;

    private String remarks;
    
    private String question;

}
