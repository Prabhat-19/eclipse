package com.payearly.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
import org.springframework.web.multipart.MultipartFile;

import com.payearly.config.Constants;
import com.payearly.domain.BankDetail;
import com.payearly.domain.EntityDetail;

import com.payearly.service.EntityDetailService;
import com.payearly.service.dto.EntityAndAddressDetailDTO;
import com.payearly.service.dto.EntityDetailDTO;
import com.payearly.service.dto.EntityNameDto;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Constants.API_URL)
public class EntityDetailResource {

    private final Logger log = LoggerFactory.getLogger(EntityDetailResource.class);

    private static final String ENTITY_NAME = "entityDetail";

    private final EntityDetailService entityDetailService;

    public EntityDetailResource(EntityDetailService entityDetailService) {
        this.entityDetailService = entityDetailService;
    }

    /**
     * {@code POST  /entityDetails}  : Creates a new entityDetails.
     * <p>
     * Creates a new entityDetails
     *
     * @param scoreCardMaster the entityDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityDetails, or with status {@code 400 (Bad Request)} 
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)}
     */
    
    @PostMapping("/entity-details")
    public ResponseEntity<EntityDetail> createEntityDetail(@RequestBody EntityDetail entityDetail) throws URISyntaxException {
        log.debug("REST request to save entityDetail : {}", entityDetail);
        EntityDetail entityDetails = entityDetailService.createEntityDetail(entityDetail);
       
        return ResponseEntity.created(new URI("/api/entity-details/" + entityDetails.getId()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A addresss is created with identifier " + entityDetails.getId()))
                .body(entityDetails);

    }

    /**
     * {@code PUT /entityDetail} : Updates an existing entityDetails.
     *
     * @param entityDetail the entityDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated address.
     * @throws URISyntaxException 
     */
    @PutMapping("/entity-details")
    public ResponseEntity<EntityDetail> updatEntityDetail(@Valid @RequestBody EntityDetail entityDetail) throws URISyntaxException {
        log.debug("REST request to update entityDetail : {}", entityDetail);
        if (entityDetail.getId() == null) {
            createEntityDetail(entityDetail);
        }
        EntityDetail entityDetails = entityDetailService.updateEntityDetail(entityDetail);

        return ResponseEntity.created(new URI("/api/entity-detail/" + entityDetails.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A entityDetail is created with identifier " + entityDetails.getId()))
                .body(entityDetails);
    }

    /**
     * {@code GET /entity-detail} : get all EntityDetail.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all EntityDetail.
     */
    @GetMapping("/entity-detail")
    public ResponseEntity<List<EntityDetail>> getAllEntityDetail(Pageable pageable) {

        final Page<EntityDetail> page = entityDetailService.getEntityDetail(pageable);
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    /**
     * {@code GET /entity-detail} : get all address.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all address.
     */
    @GetMapping("/entity-detail/{id}")
    public ResponseEntity<EntityDetail> getEntityDetail(@PathVariable("id") UUID id) {
        EntityDetail  entityDetail = entityDetailService.getEntityDetails(id);
      
        return new ResponseEntity<>(entityDetail, HttpStatus.OK);
    }
    
    @GetMapping("/list-gstin")
    public ResponseEntity<List<EntityDetailDTO>> listGstin() {
         List<EntityDetailDTO> page = entityDetailService.getAllEntityAndAddress();

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(path = "/entity-source")
    public List<EntityNameDto> getAllEntityName() {

        return entityDetailService.getAllEntity();
    }

    /**
     * {@code GET /entitity-detail/{entityDetailId}} : get all detail of entityDetail.
     *
     * @param entityDetailId the id of entityDetail.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all entityDetail.
     */
    @GetMapping(path = "/entitity-detail/{entityDetailId}")
    public EntityAndAddressDetailDTO getAllEntityName(@PathVariable UUID entityDetailId) {

        return entityDetailService.getEntityDetail(entityDetailId);
    }

    @PutMapping(path = "/entitity-detail")
    public EntityDetail updateEntityDetail(@RequestBody EntityAndAddressDetailDTO entityAndAddressDetailDTO) {

        return entityDetailService.getEntityDetail(entityAndAddressDetailDTO);
    }

    @DeleteMapping("/entitity-detail/{gstinId}")
    public void deleteEntityDetail(@PathVariable UUID gstinId) {

        entityDetailService.deleteEntityDetail(gstinId);
    }

//    @PostMapping("/entity-mapping")
//    public EntityMapping createEntityDetail(@RequestBody EntityMappingDTO entityMappingDTO) {
//
//        return entityDetailService.createEntityDetail(entityMappingDTO);
//    }
//
//    @PostMapping("/get-entity-mappings")
//    public List<EntityMapping> getAllEntityMapping(@RequestBody EntityNameDto entityname) {
//
//        return entityDetailService.getEntityMapping(entityname.getEntityName());
//    }

    @PostMapping("/bank-detail")
    public BankDetail createBankDetail(@Valid @RequestBody BankDetail bankDetail) {

        return entityDetailService.createBankDetail(bankDetail);
    }

    @PostMapping("/document-upload/{label}")
    public String entitydocumentUpload(@PathVariable String label, @Valid @NotNull @NotBlank @RequestBody MultipartFile file) {

        return entityDetailService.uploadDocument(label,file);
    }
}
