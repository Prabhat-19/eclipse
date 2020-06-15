package com.payearly.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.EntityDocuments;
import com.payearly.enums.DeletionType;
import com.payearly.repository.EntityDocumentsRepository;
import com.payearly.service.EntityDocumentsService;

@Service
@Transactional
public class EntityDocumentsServiceImpl implements EntityDocumentsService{

    private final EntityDocumentsRepository entityDocumentsRepository;
    
    public EntityDocumentsServiceImpl(EntityDocumentsRepository entityDocumentsRepository) {
        this.entityDocumentsRepository = entityDocumentsRepository;
    }

    @Override
    public EntityDocuments updateEntityDocument(@Valid EntityDocuments entityDocuments) {

        return entityDocumentsRepository.save(entityDocuments);
    }

    @Override
    public List<EntityDocuments> getAllScoreCardMaster() {

        return entityDocumentsRepository.findBydeletionType(DeletionType.LIVE);
    }

    @Override
    public EntityDocuments getScoreCardChild(UUID id) {

        return entityDocumentsRepository.findById(id).get();
    }

    @Override
    public List<EntityDocuments> createEntityDocuments(@Valid List<EntityDocuments> entityDocuments) {

        return entityDocumentsRepository.saveAll(entityDocuments);
    }

	@Override
	public void deleteEntityDocuments(UUID id) {
		Optional<EntityDocuments> documents= entityDocumentsRepository.findById(id);
		documents.get().setDeletionType(DeletionType.DELETED);
		entityDocumentsRepository.save(documents.get());
	}

}
