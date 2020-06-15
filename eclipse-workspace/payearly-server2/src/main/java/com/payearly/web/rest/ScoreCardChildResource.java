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
import com.payearly.domain.ScoreCardChild;
import com.payearly.service.ScoreCardChildService;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class ScoreCardChildResource {

    private final Logger log = LoggerFactory.getLogger(ScoreCardMasterResource.class);

    private static final String ENTITY_NAME = "scoreCardChild";

    private final ScoreCardChildService scoreCardChildService;
    
    public ScoreCardChildResource(ScoreCardChildService scoreCardChildService) {
        super();
        this.scoreCardChildService = scoreCardChildService;
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
    @PostMapping("/score-card-child")
    public ResponseEntity<List<ScoreCardChild> > creatScoreChild(@Valid @RequestBody List<ScoreCardChild> scoreCardChild) throws URISyntaxException {
        log.debug("REST request to save scoreCardMaster : {}", scoreCardChild);
        List<ScoreCardChild>  scoreCardMasters = scoreCardChildService.createMasterScoreChild(scoreCardChild);
            return ResponseEntity.created(new URI("/api/score-card-master/" + scoreCardMasters.size()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A scoreCardChild is created with identifier " + scoreCardMasters.size()))
                .body(scoreCardMasters);
        
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
    @PostMapping("/bank-score-card")
    public ResponseEntity<List<ScoreCardChild> > createBankScoreChild(@Valid @RequestBody List<ScoreCardChild> scoreCardChild) throws URISyntaxException {
        log.debug("REST request to save scoreCardMaster : {}", scoreCardChild);
        List<ScoreCardChild>  scoreCardMasters = scoreCardChildService.createBankScoreChild(scoreCardChild);
            return ResponseEntity.created(new URI("/api/score-card-master/" + scoreCardMasters.size()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A scoreCardChild is created with identifier " + scoreCardMasters.size()))
                .body(scoreCardMasters);
        
    }

    /**
     * {@code PUT /users} : Updates an existing scoreCardMaster.
     *
     * @param scoreCardMaster the scoreCardMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scoreCardMaster.
     * @throws URISyntaxException 
     */
    @PutMapping("/score-card-child")
    public ResponseEntity<ScoreCardChild> updateScoreCardChild(@Valid @RequestBody ScoreCardChild scoreCardChild) throws URISyntaxException {
        log.debug("REST request to update scoreCardMaster :  {}", scoreCardChild);
        if (scoreCardChild.getId() == null) {
            throw new IllegalArgumentException("id required");
        }
        ScoreCardChild scoreCardMaster = scoreCardChildService.updateScoreCardChild(scoreCardChild);

        return ResponseEntity.created(new URI("/api/score-card-child/" + scoreCardMaster.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A scoreCardChild is created with identifier " + scoreCardMaster.getId()))
                .body(scoreCardMaster);
    }

    /**
     * {@code GET /score-card-child} : get all scoreCardMaster.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all scoreCardMasters.
     */
    @GetMapping("/score-card-child")
    public ResponseEntity<List<ScoreCardChild>> getAllScoreCardchild(Pageable pageable) {
        
        final Page<ScoreCardChild> page = scoreCardChildService.getAllScoreCardMaster(pageable);
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    /**
     * {@code GET /score-card-child} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/score-card-child/{id}")
    public ResponseEntity<ScoreCardChild> getScoreCardChild(@PathVariable("id") UUID id) {
        
        ScoreCardChild  scoreCardMaster = scoreCardChildService.getScoreCardChild(id);
    //   HttpHeders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(scoreCardMaster, HttpStatus.OK);
    }
}
