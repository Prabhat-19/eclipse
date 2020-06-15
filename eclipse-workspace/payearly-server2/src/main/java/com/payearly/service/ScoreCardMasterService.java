package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.payearly.domain.ScoreCardMaster;
import com.payearly.service.dto.ScoreCardMasterDTO;

public interface ScoreCardMasterService {

    ScoreCardMaster createMasterScoreCard(@Valid ScoreCardMaster scoreCardMaster);

    ScoreCardMaster updateScoreCardMaster(ScoreCardMaster scoreCardMaster);

    List<ScoreCardMasterDTO> getAllScoreCardMaster();

    ScoreCardMaster getScoreCardMaster(UUID id);

}
