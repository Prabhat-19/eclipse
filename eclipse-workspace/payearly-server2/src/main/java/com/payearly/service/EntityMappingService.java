package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.payearly.domain.EntityMapping;

public interface EntityMappingService {

    EntityMapping createEntityMapping(@Valid EntityMapping entityMapping);

    EntityMapping updateEntityMapping(@Valid EntityMapping entityMapping);

    Page<EntityMapping> getEntityMapping(Pageable pageable);

    List<EntityMapping> getEntityMapping(UUID id);

}
