package com.payearly.serviceImpl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.EntityMapping;
import com.payearly.repository.EntityMappingRepository;
import com.payearly.service.EntityMappingService;

@Service
@Transactional
public class EntityMappingServiceImpl implements EntityMappingService{

     private final EntityMappingRepository entityMappingRepository;

    public EntityMappingServiceImpl(EntityMappingRepository entityMappingRepository) {
        super();
        this.entityMappingRepository = entityMappingRepository;
    }

    @Override
    public EntityMapping createEntityMapping(@Valid EntityMapping entityMapping) {

        return entityMappingRepository.save(entityMapping);
    }

    @Override
    public EntityMapping updateEntityMapping(@Valid EntityMapping entityMapping) {

        return entityMappingRepository.save(entityMapping);
    }

    @Override
    public Page<EntityMapping> getEntityMapping(Pageable pageable) {

        return entityMappingRepository.findAll(pageable);
    }

    @Override
    public List<EntityMapping> getEntityMapping(UUID id) {

        return entityMappingRepository.findByEntityDetail_id(id);
    }

}
