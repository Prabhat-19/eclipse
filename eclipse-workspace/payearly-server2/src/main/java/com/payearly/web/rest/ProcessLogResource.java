package com.payearly.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payearly.config.Constants;
import com.payearly.domain.ProcessLog;
import com.payearly.repository.EntityDetailRepository;
import com.payearly.repository.ProcessLogRepository;
import com.payearly.service.ProcessLogService;
import com.payearly.service.dto.EntityCreationDTO;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;
import com.payearly.web.rest.errors.ProcessLogAlreadyPresentException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)

public class ProcessLogResource {

    private final Logger log = LoggerFactory.getLogger(ProcessLogResource.class);

    private static final String ENTITY_NAME = "ProcessLog";

    private final ProcessLogService processLogService;

    private final EntityDetailRepository entityDetailRepository;

    public ProcessLogResource(ProcessLogService processLogService, EntityDetailRepository entityDetailRepository) {
        this.processLogService = processLogService;
        this.entityDetailRepository = entityDetailRepository;
    }

    /**
     * {@code POST  /processLog} : Creates a new processLog.
     * <p>
     * Creates a new processLog if the login and email are not already used, and
     * sends an mail with an activation link. The user needs to be activated on
     * creation.
     *
     * @param entityCreationDTO the processLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and
     * with body the new processLog, or with status {@code 400 (Bad Request)} if
     * the processLog already use or wrong gstn.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the
     * processLog is already present or wrong gstn.
     */
    @PostMapping("/process-log")
    public ResponseEntity<ProcessLog> createProcessLog(@Valid @RequestBody EntityCreationDTO entityCreationDTO)
            throws URISyntaxException, IOException {
        log.debug("REST request to save ProcessLog : {}", entityCreationDTO);
        if (entityDetailRepository.findBygstin(entityCreationDTO.getGstin()).isPresent()) {
            throw new ProcessLogAlreadyPresentException();
        } else if (entityDetailRepository.findByentityPan(entityCreationDTO.getGstin()).isPresent()) {
            throw new ProcessLogAlreadyPresentException();
        } else {
            ProcessLog processLog = processLogService.createProcess(entityCreationDTO);
            return ResponseEntity.created(new URI("/api/process-log/" + entityCreationDTO.getGstin()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A entity and processLog is created with identifier " + entityCreationDTO.getGstin()))
                    .body(processLog);
        }
    }
}
