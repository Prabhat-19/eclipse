package com.payearly.serviceImpl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.domain.EntityCategory;
import com.payearly.repository.EntityCategoryRepository;
import com.payearly.service.EntityCategoryService;

@Service
@Transactional
public class EntityCategoryServiceImpl implements EntityCategoryService{

	private final EntityCategoryRepository entityCategoryRepository;

	public EntityCategoryServiceImpl(EntityCategoryRepository entityCategoryRepository) {
		super();
		this.entityCategoryRepository = entityCategoryRepository;
	}

	@Override
	public EntityCategory getEntityCategory(UUID id) {

		return entityCategoryRepository.findById(id).get();
	}

	@Override
	public EntityCategory createEntityCategory(@Valid EntityCategory entityCategory) {

		return entityCategoryRepository.save(entityCategory);
	}

	@Override
	public EntityCategory updateEntityCategory(@Valid EntityCategory entityCategory) {

		return entityCategoryRepository.save(entityCategory);
	}

	@Override
	public List<EntityCategory> getAllEntityCategory() {

		return entityCategoryRepository.findAll();
	}

}
