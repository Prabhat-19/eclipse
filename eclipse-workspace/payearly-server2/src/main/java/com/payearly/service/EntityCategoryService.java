package com.payearly.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.payearly.domain.EntityCategory;

public interface EntityCategoryService {

	EntityCategory getEntityCategory(UUID id);

	EntityCategory createEntityCategory(@Valid EntityCategory entityCategory);

	EntityCategory updateEntityCategory(@Valid EntityCategory entityCategory);

	List<EntityCategory> getAllEntityCategory();

}
