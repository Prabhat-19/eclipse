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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.EntityDocuments;
import com.payearly.service.EntityDocumentsService;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)

public class EntityDocumentsResource {

    private final Logger log = LoggerFactory.getLogger(EntityDocumentsResource.class);

    private static final String ENTITY_NAME = "entityDocuments";

    private final EntityDocumentsService entityDocumentsService;

    public EntityDocumentsResource(EntityDocumentsService entityDocumentsService) {
        super();
        this.entityDocumentsService = entityDocumentsService;
    }

    /**
     * {@code POST  /score-card-child}  : Creates a new scoreCardChild.
     * <p>
     * Creates a new scoreCardMaster
     *
     * @param scoreCardMaster the scoreCardChild to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scoreCardChild, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)}
     */
    @PostMapping("/entity-document")
    public ResponseEntity<List<EntityDocuments>> createMasterScoreChild(@Valid @RequestBody List<EntityDocuments> entityDocuments) throws URISyntaxException {
        log.debug("REST request to save entityDocuments : {}", entityDocuments);
        List<EntityDocuments> entityDocument = entityDocumentsService.createEntityDocuments(entityDocuments);
            
        return ResponseEntity.created(new URI("/api/entity-documents/" + entityDocument.stream().count()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A entityDocuments is created with identifier " + entityDocument.toString()))
                .body(entityDocument);

    }

    /**
     * {@code PUT /entityDocuments} : Updates an existing scoreCardMaster.
     *
     * @param scoreCardMaster the scoreCardMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scoreCardMaster.
     * @throws URISyntaxException 
     */
    @PutMapping("/entity-document")
    public ResponseEntity<EntityDocuments> updateEntityDocument(@Valid @RequestBody EntityDocuments entityDocuments) throws URISyntaxException {
        log.debug("REST request to update scoreCardMaster : {}", entityDocuments);
        if (entityDocuments.getId() == null) {
           // createMasterScoreChild(entityDocuments);
        throw new IllegalArgumentException("id must be give in the out call");
        }
        EntityDocuments entityDocument = entityDocumentsService.updateEntityDocument(entityDocuments);

        return ResponseEntity.created(new URI("/api/score-card-child/" + entityDocument.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A entityDocument is created with identifier " + entityDocument.getId()))
                .body(entityDocument);
    }

    /**
     * {@code GET /entity-document} : get all scoreCardMaster.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all scoreCardMasters.
     */
    @GetMapping("/entity-document")
    public ResponseEntity<List<EntityDocuments>> getAllScoreCardchild() {
        
         List<EntityDocuments> page = entityDocumentsService.getAllScoreCardMaster();
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * {@code GET /entity-document} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/entity-document/{id}")
    public ResponseEntity<EntityDocuments> getEntityDocuments(@PathVariable UUID id) {
        EntityDocuments  scoreCardMaster = entityDocumentsService.getScoreCardChild(id);
      
        return new ResponseEntity<>(scoreCardMaster, HttpStatus.OK);
    }
    

    /**
     * {@code delete /ledger-master} : get one ledgerMaster.
     *
     * @param id  id of LedgerMaster.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} 
     */
    @DeleteMapping("/ledger-master/{id}")
    public void deleteEntityDocuments(@PathVariable UUID id) {
     log.debug("REST request for deleting EntityDocuments : {}", id);

      entityDocumentsService.deleteEntityDocuments(id);
	}
}
