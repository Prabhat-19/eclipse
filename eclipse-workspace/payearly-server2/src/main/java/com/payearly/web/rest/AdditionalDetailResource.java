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
import com.payearly.domain.AdditionalDetail;
import com.payearly.service.AdditionalDetailService;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class AdditionalDetailResource {

    private final Logger log = LoggerFactory.getLogger(AdditionalDetailResource.class);

    private static final String ENTITY_NAME = "additionalDetail";

    private final AdditionalDetailService additionalDetailService;

    public AdditionalDetailResource(AdditionalDetailService additionalDetailService) {
        super();
        this.additionalDetailService = additionalDetailService;
    }

    /**
     * {@code POST  /additional-detail}  : Creates a new additionalDetail.
     * <p>
     * Creates a new scoreCardMaster
     *
     * @param additionalDetail the additionalDetail to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new additionalDetail, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)}
     */
    @PostMapping("/additional-detail")
    public ResponseEntity<List<AdditionalDetail>> createAdditionalDetail(@Valid @RequestBody List<AdditionalDetail> additionalDetail) throws URISyntaxException {
        log.debug("REST request to save additionalDetail : {}", additionalDetail);
        List<AdditionalDetail> additionalDetails = additionalDetailService.createMasterScoreChild(additionalDetail);
            return ResponseEntity.created(new URI("/api/additional-detail/" + additionalDetails.size()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A scoreCardChild is created with identifier " + additionalDetails.size()))
                .body(additionalDetails);
        
    }

    /**
     * {@code PUT /additional-detail} : Updates an existing additionalDetail.
     *
     * @param scoreCardMaster the additionalDetail to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated additionalDetail.
     * @throws URISyntaxException 
     */
    @PutMapping("/additional-detail")
    public ResponseEntity<AdditionalDetail> updateAdditionalDetail(@Valid @RequestBody AdditionalDetail additionalDetail) throws URISyntaxException {
        log.debug("REST request to update scoreCardMaster : {}", additionalDetail);
        if (additionalDetail.getId() == null) {
            throw new IllegalArgumentException("id must be persent in the update call");
        }
        AdditionalDetail additionalDetails = additionalDetailService.updateScoreCardChild(additionalDetail);

        return ResponseEntity.created(new URI("/api/additional-detail" + additionalDetails.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A scoreCardChild is created with identifier " + additionalDetails.getId()))
                .body(additionalDetails);
    }

    /**
     * {@code GET /additional-detail} : get all additionalDetail.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all scoreCardMasters.
     */
    @GetMapping("/additional-detail")
    public ResponseEntity<List<AdditionalDetail>> getAllAdditionalDetail(Pageable pageable) {
        
        final Page<AdditionalDetail> page = additionalDetailService.getAllScoreCardMaster(pageable);
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    /**
     * {@code GET /additional-detail} : get all additionalDetail.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/additional-detail/{id}")
    public ResponseEntity<AdditionalDetail> getAdditionalDetail(@PathVariable("id") UUID id) {
        
        AdditionalDetail  additionalDetail = additionalDetailService.getAdditionalDetail(id);
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(additionalDetail, HttpStatus.OK);
    }
}
