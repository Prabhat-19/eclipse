package com.payearly.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.ConfiguratorLookUp;
import com.payearly.domain.ScoreCardMaster;
import com.payearly.repository.ConfiguratorLookUpDetailRepository;
import com.payearly.repository.ScoreCardMasterRepository;
import com.payearly.service.ScoreCardMasterService;
import com.payearly.service.dto.ConfiguratorLookUpDetailDTO;
import com.payearly.service.dto.ConfiguratoreLookUpDTO;
import com.payearly.service.dto.ScoreCardMasterDTO;

@Service
@Transactional
public class ScoreCardMasterServiceImpl implements ScoreCardMasterService{

   private final ScoreCardMasterRepository scoreCardMasterRepository;
   
   private final ConfiguratorLookUpDetailRepository lookUpDetailRepository;

    public ScoreCardMasterServiceImpl(ScoreCardMasterRepository scoreCardMasterRepository,
		ConfiguratorLookUpDetailRepository lookUpDetailRepository) {
	super();
	this.scoreCardMasterRepository = scoreCardMasterRepository;
	this.lookUpDetailRepository = lookUpDetailRepository;
}

	@Override
    public ScoreCardMaster createMasterScoreCard(@Valid ScoreCardMaster scoreCardMaster) {
  
        return scoreCardMasterRepository.save(scoreCardMaster);
    }

    @Override
    public ScoreCardMaster updateScoreCardMaster(ScoreCardMaster scoreCardMaster) {

        return scoreCardMasterRepository.save(scoreCardMaster);
    }

    @Override
    public List<ScoreCardMasterDTO> getAllScoreCardMaster() {

        return scoreCardMasterRepository.findAll().stream().map(scorecard -> {
         ScoreCardMasterDTO masterDTO = new ScoreCardMasterDTO();
         ConfiguratoreLookUpDTO  lookUpDTO = new ConfiguratoreLookUpDTO();
         
         masterDTO.setId(scorecard.getId());
         masterDTO.setDocumentProof(scorecard.getDocumentProof());
         masterDTO.setQuestion(scorecard.getQuestion());
         masterDTO.setRejectionCriteria(scorecard.getRejectionCriteria());
         masterDTO.setRemarks(scorecard.getRemarks());
         masterDTO.setScoreCardCategoryType(scorecard.getScoreCardCategoryType());
         masterDTO.setWeightage(scorecard.getWeightage());
         lookUpDTO.setDiscription(scorecard.getCriteriaLookUp().getDiscription());
         lookUpDTO.setId(scorecard.getCriteriaLookUp().getId());
         lookUpDTO.setName(scorecard.getCriteriaLookUp().getName());
         lookUpDTO.setConfiguratorLookUpDetail(lookUpDetailRepository.findByconfiguratorLookUp_id(lookUpDTO.getId()).stream().map(confDetail -> {
             ConfiguratorLookUpDetailDTO configuratorLookUpDetailDTO = new ConfiguratorLookUpDetailDTO();
             configuratorLookUpDetailDTO.setCategoryType(confDetail.getCategoryType());
             configuratorLookUpDetailDTO.setValue(confDetail.getValue());
             configuratorLookUpDetailDTO.setDiscription(confDetail.getDiscription());
             configuratorLookUpDetailDTO.setId(confDetail.getId());
             
             return configuratorLookUpDetailDTO;
           }).collect(Collectors.toList()));
        
         masterDTO.setCriteriaLookUp(lookUpDTO);
        

         return masterDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ScoreCardMaster getScoreCardMaster(UUID id) {

        return scoreCardMasterRepository.getOne(id);
    }

}
