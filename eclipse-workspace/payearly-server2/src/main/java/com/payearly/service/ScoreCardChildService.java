package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.payearly.domain.ScoreCardChild;

public interface ScoreCardChildService {

	List<ScoreCardChild>  createMasterScoreChild(@Valid List<ScoreCardChild> scoreCardChild);

    ScoreCardChild updateScoreCardChild(@Valid ScoreCardChild scoreCardChild);

    Page<ScoreCardChild> getAllScoreCardMaster(Pageable pageable);

    ScoreCardChild getScoreCardChild(UUID id);

	List<ScoreCardChild> createBankScoreChild(@Valid List<ScoreCardChild> scoreCardChild);

}
