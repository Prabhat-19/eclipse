package com.payearly.web.rest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.EntityCategory;
import com.payearly.service.EntityCategoryService;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class EntityCategoryResource {

    private final Logger log = LoggerFactory.getLogger(EntityCategoryResource.class);

    private static final String ENTITY_NAME = "entityCategory";

    private final EntityCategoryService entityCategoryService;
    
    public EntityCategoryResource(EntityCategoryService entityCategoryService) {
		super();
		this.entityCategoryService = entityCategoryService;
	}

	/**
     * {@code POST  /entity-category}  : Creates a new scoreCardChild.
     * <p>
     * Creates a new entityCategory
     *
     * @param scoreCardMaster the scoreCardChild to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scoreCardChild, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)}
     */
    @PostMapping("/entity-category")
    public ResponseEntity<EntityCategory> createEntityCategory(@Valid @RequestBody EntityCategory entityCategory) throws URISyntaxException {
        log.debug("REST request to save EntityCategory : {}", entityCategory);
        EntityCategory scoreCardMasters = entityCategoryService.createEntityCategory(entityCategory);
            return ResponseEntity.created(new URI("/api/entity-category/" + scoreCardMasters.getId()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A EntityCategory is created with identifier " + scoreCardMasters.getId()))
                .body(scoreCardMasters);
    }

    /**
     * {@code PUT /entity-category} : Updates an existing EntityCategory.
     *
     * @param entityCategory the entityCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated EntityCategory.
     * @throws URISyntaxException 
     */
    @PutMapping("/entity-category")
    public ResponseEntity<EntityCategory> updateEntityCategory(@Valid @RequestBody EntityCategory entityCategory) throws URISyntaxException {
        log.debug("REST request to update  EntityCategory :  {}", entityCategory);
        if (entityCategory.getId() == null) {
            throw new IllegalArgumentException("id required");
        }
        EntityCategory entityCategorys = entityCategoryService.updateEntityCategory(entityCategory);

        return ResponseEntity.created(new URI("/api/entity-category/" + entityCategorys.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A EntityCategory is created with identifier " + entityCategorys.getId()))
                .body(entityCategorys);
    }

    /**
     * {@code GET /entity-category : get all scoreCardMaster.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all scoreCardMasters.
     */
    @GetMapping("/entity-category")
    public ResponseEntity<List<EntityCategory>> getEntityCategory() {

        List<EntityCategory> page = entityCategoryService.getAllEntityCategory();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * {@code GET /entity-category} : get an EntityCategory.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body an EntityCategory.
     */
    @GetMapping("/entity-category/{id}")
    public ResponseEntity<EntityCategory> getEntityCategory(@PathVariable("id") UUID id) {
        
        EntityCategory  entityCategory = entityCategoryService.getEntityCategory(id);
        return new ResponseEntity<>(entityCategory, HttpStatus.OK);
    }
}
