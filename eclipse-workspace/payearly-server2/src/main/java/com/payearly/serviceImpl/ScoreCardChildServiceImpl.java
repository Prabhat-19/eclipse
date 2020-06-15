package com.payearly.serviceImpl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.ScoreCardChild;
import com.payearly.enums.ScoreCardType;
import com.payearly.repository.ScoreCardChildRepository;
import com.payearly.repository.ScoreCardMasterRepository;
import com.payearly.service.ScoreCardChildService;

@Service
@Transactional
public class ScoreCardChildServiceImpl implements ScoreCardChildService{

    private final ScoreCardChildRepository scoreCardChildRepository;

    public ScoreCardChildServiceImpl(ScoreCardChildRepository scoreCardChildRepository) {
        super();
        this.scoreCardChildRepository = scoreCardChildRepository;
    }

    @Override
    public List<ScoreCardChild>  createMasterScoreChild(@Valid List<ScoreCardChild>  scoreCardChild) {

    	scoreCardChild.stream().forEach(scord ->scord.setScoreCardType(ScoreCardType.PAYEARLY));
    	return scoreCardChildRepository.saveAll(scoreCardChild);
    }

    @Override
    public ScoreCardChild updateScoreCardChild(@Valid ScoreCardChild scoreCardChild) {

    	return scoreCardChildRepository.save(scoreCardChild);
    }

    @Override
    public Page<ScoreCardChild> getAllScoreCardMaster(Pageable pageable) {

    	return scoreCardChildRepository.findAll(pageable);
    }

    @Override
    public ScoreCardChild getScoreCardChild(UUID id) {

    	return scoreCardChildRepository.findById(id).get();
    }

	@Override
	public List<ScoreCardChild> createBankScoreChild(@Valid List<ScoreCardChild> scoreCardChild) {
		scoreCardChild.stream().forEach(scord ->scord.setScoreCardType(ScoreCardType.BANK));

		return scoreCardChildRepository.saveAll(scoreCardChild);
	}

}
