package com.payearly.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.payearly.domain.EntityDocuments;
import com.payearly.domain.EntityMapping;
import com.payearly.service.EntityDocumentsService;
import com.payearly.service.EntityMappingService;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)

public class EntityMappingResource {

    
    private final Logger log = LoggerFactory.getLogger(EntityMappingResource.class);

    private static final String ENTITY_NAME = "EntityMapping";

    private final EntityMappingService entityMappingService;

    public EntityMappingResource(EntityMappingService entityMappingService) {
        super();
        this.entityMappingService = entityMappingService;
    }

    /**
     * {@code POST  /entity-mapping}  : Creates a new entityMappings.
     * <p>
     * Creates a new entityMappings
     *
     * @param scoreCardMaster the entityMappings to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityMappings, or with status {@code 400 (Bad Request)} 
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)}
     */
    
    @PostMapping("/entity-mapping")
    public ResponseEntity<EntityMapping> createEntityMapping(@Valid @RequestBody EntityMapping entityMapping) throws URISyntaxException {
        log.debug("REST request to save EntityMapping : {}", entityMapping);
        EntityMapping entityMappings = entityMappingService.createEntityMapping(entityMapping);
       
        return ResponseEntity.created(new URI("/api/entity-documents/" + entityMappings.getId()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A entityMapping is created with identifier " + entityMappings.getId()))
                    .body(entityMappings);

    }

    /**
     * {@code PUT /entity-mappings} : Updates an existing entityMappings.
     *
     * @param entityMappings the entityMappings to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityMappings.
     * @throws URISyntaxException 
     */
    @PutMapping("/entity-mappings")
    public ResponseEntity<EntityMapping> updateEntityDocument(@Valid @RequestBody EntityMapping entityMapping) throws URISyntaxException {
        log.debug("REST request to update scoreCardMaster : {}", entityMapping);
        if (entityMapping.getId() == null) {
            createEntityMapping(entityMapping);
        }
        EntityMapping entityMappings = entityMappingService.updateEntityMapping(entityMapping);

        return ResponseEntity.created(new URI("/api/entity-mappings/" + entityMappings.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A entityMappings is created with identifier " + entityMappings.getId()))
                .body(entityMappings);
    }

    /**
     * {@code GET /entity-mappings} : get all entityMappings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all entityMappings.
     */
    @GetMapping("/entity-mappings")
    public ResponseEntity<List<EntityMapping>> getAllEntityMapping(Pageable pageable) {
        final Page<EntityMapping> page = entityMappingService.getEntityMapping(pageable);

        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    /**
     * {@code GET /entity-mapping} : get all mntityMapping.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all EntityMapping.
     */
    @GetMapping("/entity-mapping/{id}")
    public ResponseEntity<List<EntityMapping>> getEntityMapping(@PathVariable("id") UUID id) {
       List<EntityMapping>   entityMapping = entityMappingService.getEntityMapping(id);
      
        return new ResponseEntity<>(entityMapping, HttpStatus.OK);
    }
}
