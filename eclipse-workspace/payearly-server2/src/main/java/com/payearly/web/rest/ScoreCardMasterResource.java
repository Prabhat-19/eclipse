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
import com.payearly.domain.ScoreCardMaster;
import com.payearly.repository.ScoreCardMasterRepository;
import com.payearly.service.ScoreCardMasterService;
import com.payearly.service.dto.ScoreCardMasterDTO;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)

public class ScoreCardMasterResource {

    private final Logger log = LoggerFactory.getLogger(ScoreCardMasterResource.class);

    private static final String ENTITY_NAME = "ScoreCardMaster";

    private final ScoreCardMasterRepository scoreCardMasterRepository;
    
    private final ScoreCardMasterService scoreCardMasterService;
    
    public ScoreCardMasterResource(ScoreCardMasterRepository scoreCardMasterRepository,
            ScoreCardMasterService scoreCardMasterService) {
        super();
        this.scoreCardMasterRepository = scoreCardMasterRepository;
        this.scoreCardMasterService = scoreCardMasterService;
    }

    /**
     * {@code POST  /scoreCardMaster}  : Creates a new user.
     * <p>
     * Creates a new scoreCardMaster
     *
     * @param scoreCardMaster the scoreCardMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the login or email is already in use.
     */
    @PostMapping("/score-card-master")
    public ResponseEntity<ScoreCardMaster> createMasterScoreCard(@Valid @RequestBody ScoreCardMaster scoreCardMaster) throws URISyntaxException {
        log.debug("REST request to save scoreCardMaster : {}", scoreCardMaster);
          ScoreCardMaster scoreCardMasters = scoreCardMasterService.createMasterScoreCard(scoreCardMaster);
            return ResponseEntity.created(new URI("/api/score-card-master/" + scoreCardMasters.getId()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A entity and processLog is created with identifier " + scoreCardMasters.getId()))
                .body(scoreCardMasters);
        
    }

    /**
     * {@code PUT /users} : Updates an existing scoreCardMaster.
     *
     * @param scoreCardMaster the scoreCardMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scoreCardMaster.
     * @throws URISyntaxException 
     */
    @PutMapping("/score-card-master")
    public ResponseEntity<ScoreCardMaster> updateScoreCardMaster(@Valid @RequestBody ScoreCardMaster scoreCardMasters) throws URISyntaxException {
        log.debug("REST request to update scoreCardMaster : {}", scoreCardMasters);
        if (scoreCardMasters.getId() == null) {
            createMasterScoreCard(scoreCardMasters);
        }
        ScoreCardMaster scoreCardMaster = scoreCardMasterService.updateScoreCardMaster(scoreCardMasters);

        return ResponseEntity.created(new URI("/api/users/" + scoreCardMaster.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A user is created with identifier " + scoreCardMaster.getId()))
                .body(scoreCardMaster);
    }

    /**
     * {@code GET /score-card-master} : get all scoreCardMaster.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all scoreCardMasters.
     */
    @GetMapping("/score-card-master")
    public ResponseEntity<List<ScoreCardMasterDTO>> getAllScoreCardMaster() {

         List<ScoreCardMasterDTO> page = scoreCardMasterService.getAllScoreCardMaster();
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
     
         return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * {@code GET /score-card-master} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/score-card-master/{id}")
    public ResponseEntity<ScoreCardMaster> getScoreCardMaster(@PathVariable("id") UUID id) {

        ScoreCardMaster  scoreCardMaster = scoreCardMasterService.getScoreCardMaster(id);
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(scoreCardMaster, HttpStatus.OK);
    }
}
