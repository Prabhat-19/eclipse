package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.payearly.domain.EntityDocuments;

public interface EntityDocumentsService {

    EntityDocuments updateEntityDocument(@Valid EntityDocuments entityDocuments);

    List<EntityDocuments> getAllScoreCardMaster();

    EntityDocuments getScoreCardChild(UUID id);

    List<EntityDocuments> createEntityDocuments(@Valid List<EntityDocuments> entityDocuments);

	void deleteEntityDocuments(UUID id);


}
