package com.payearly.web.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.payearly.config.ApplicationProperties;
import com.payearly.config.Constants;
import com.payearly.domain.LedgerMaster;
import com.payearly.service.LedgerMasterService;
import com.payearly.service.util.HeaderUtil;
import com.payearly.web.rest.errors.BadRequestAlertException;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.API_URL)
public class LedgerMasterResource {
	
    private final Logger log = LoggerFactory.getLogger(LedgerMasterResource.class);

    private static final String ENTITY_NAME = "ledgerMaster";

    private final LedgerMasterService ledgerMasterService;
    
    private final ApplicationProperties applicationProperties;
    
    private final ServletContext servletContext;

    public LedgerMasterResource(LedgerMasterService ledgerMasterService, ApplicationProperties applicationProperties,
			ServletContext servletContext) {
		super();
		this.ledgerMasterService = ledgerMasterService;
		this.applicationProperties = applicationProperties;
		this.servletContext = servletContext;
	}

	/**
     * {@code POST  /ledger-master}  : Creates a new ledgerMaster.
     * <p>
     * Creates a new ledgerMaster
     *
     * @param ledgerMaster the ledgerMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scoreCardChild, or with status {@code 400 (Bad Request)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)}
     */
    @PostMapping("/ledger-master")
    public ResponseEntity<LedgerMaster> createLedgerMaster(@Valid @RequestBody LedgerMaster ledgerMaster) throws URISyntaxException {
        log.debug("REST request to save ledgerMaster : {}", ledgerMaster);
        LedgerMaster ledgerMasters = ledgerMasterService.createMasterScoreChild(ledgerMaster);
            return ResponseEntity.created(new URI("/api/ledger-master/" + ledgerMasters.getId()))
                    .headers(HeaderUtil.createAlert(ENTITY_NAME, "A ledgerMaster is created with identifier " + ledgerMasters.getId()))
                .body(ledgerMasters);
        
    }

    /**
     * {@code PUT /ledger-master} : Updates an existing ledgerMaster.
     *
     * @param scoreCardMaster the ledgerMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scoreCardMaster.
     * @throws URISyntaxException 
     */
    @PutMapping("/ledger-master")
    public ResponseEntity<LedgerMaster> updateLedgerMaster(@Valid @RequestBody LedgerMaster ledgerMaster) throws URISyntaxException {
        log.debug("REST request to update ledgerMaster : {}", ledgerMaster);
        if (ledgerMaster.getId() == null) {
        	createLedgerMaster(ledgerMaster);
        }
        LedgerMaster ledgerMasters = ledgerMasterService.updateScoreCardChild(ledgerMaster);

        return ResponseEntity.created(new URI("/api/ledger-master/" + ledgerMasters.getId()))
                .headers(HeaderUtil.createAlert(ENTITY_NAME,  "A ledgerMaster is created with identifier " + ledgerMasters.getId()))
                .body(ledgerMaster);
    }

    /**
     * {@code GET /ledger-master} : get all ledgerMaster.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all scoreCardMasters.
     */
    @GetMapping("/ledger-master")
    public ResponseEntity<List<LedgerMaster>> getAllLedgerMaster(Pageable pageable) {
        final List<LedgerMaster> page = ledgerMasterService.getAllScoreCardMaster(pageable);
     
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     * {@code GET /ledger-master} : get all ledgerMaster.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/ledger-master/{id}")
    public ResponseEntity<LedgerMaster> getLedgerMaster(@PathVariable("id") UUID id) {
    	LedgerMaster  scoreCardMaster = ledgerMasterService.getScoreCardChild(id);

       return new ResponseEntity<>(scoreCardMaster, HttpStatus.OK);
    }

    /**
     * {@code delete /ledger-master} : get one ledgerMaster.
     *
     * @param id  id of LedgerMaster.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} 
     */
    @DeleteMapping("/ledger-master/{id}")
    public void deleteLedgerMaster(@PathVariable UUID id) {
     log.debug("REST request for deleting LedgerMaster : {}", id);

     ledgerMasterService.deleteLedgerMaster(id);
	}

    @PostMapping("/ledger-upload")
    public ResponseEntity<String> ledgerDocumentUpload(@Valid @NotNull @NotBlank @NotEmpty @RequestBody MultipartFile file) {
     String string = ledgerMasterService.uploadDocument(file);
        return ResponseEntity.ok(string);
    } 

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@Valid @NotNull @NotEmpty @NotBlank @RequestParam String fileName) throws IOException {
 
        log.debug("REST request to download  file : {}", fileName);
        MediaType mediaType = getMediaTypeForFileName(this.servletContext, fileName);

        File file = new File(applicationProperties.getUploadir() + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

	private MediaType getMediaTypeForFileName(ServletContext servletContext2, String fileName) {
		String mineType = servletContext.getMimeType(fileName);
		try{
	        MediaType mediaType = MediaType.parseMediaType(mineType);
	        return mediaType;
		     } catch (Exception e) {
	       return MediaType.APPLICATION_OCTET_STREAM;
	}
 } 
}
