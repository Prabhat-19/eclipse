package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.payearly.domain.LedgerMaster;

public interface LedgerMasterService {

	LedgerMaster createMasterScoreChild(@Valid LedgerMaster ledgerMaster);

	LedgerMaster updateScoreCardChild(@Valid LedgerMaster ledgerMaster);

	List<LedgerMaster> getAllScoreCardMaster(Pageable pageable);

	LedgerMaster getScoreCardChild(UUID id);

	void deleteLedgerMaster(UUID id);

	String uploadDocument(@Valid @NotBlank @NotEmpty MultipartFile file);

}
