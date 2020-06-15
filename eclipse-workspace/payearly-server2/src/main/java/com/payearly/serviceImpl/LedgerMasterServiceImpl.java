package com.payearly.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.payearly.config.ApplicationProperties;
import com.payearly.domain.LedgerMaster;
import com.payearly.enums.DeletionType;
import com.payearly.repository.LedgerMasterRepository;
import com.payearly.service.LedgerMasterService;


@Service
@Transactional
public class LedgerMasterServiceImpl implements LedgerMasterService{

    private final LedgerMasterRepository ledgerMasterRepository;
    
    private final ApplicationProperties applicationProperties;
    
    private final ServletContext servletContext;

	public LedgerMasterServiceImpl(LedgerMasterRepository ledgerMasterRepository,
			ApplicationProperties applicationProperties, ServletContext servletContext) {
		super();
		this.ledgerMasterRepository = ledgerMasterRepository;
		this.applicationProperties = applicationProperties;
		this.servletContext = servletContext;
	}

	@Override
    public LedgerMaster createMasterScoreChild(@Valid LedgerMaster ledgerMaster) {

       return ledgerMasterRepository.save(ledgerMaster);
     }

    @Override
    public LedgerMaster updateScoreCardChild(@Valid LedgerMaster ledgerMaster) {
       return ledgerMasterRepository.save(ledgerMaster);
    }

    @Override
    public List<LedgerMaster> getAllScoreCardMaster(Pageable pageable) {
       return ledgerMasterRepository.findAll();
    }

    @Override
    public LedgerMaster getScoreCardChild(UUID id) {
      return ledgerMasterRepository.findById(id).get();
     }

    @Override
    public void deleteLedgerMaster(UUID id) {
      
      Optional<LedgerMaster> ledgerMaster = ledgerMasterRepository.findById(id);
      ledgerMaster.get().setDeletionType(DeletionType.DELETED);
      ledgerMasterRepository.save(ledgerMaster.get());
	}

    @Override
    public String uploadDocument(@Valid MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Integer index = fileName.indexOf(".");
        String ext = fileName.substring(index);
        String name = fileName.substring(0, index);
        String instant = Instant.now().toString();
        fileName = name.concat("-").concat(instant).concat(ext);
        try {
            if(fileName.contains("..")) {
                throw new IllegalArgumentException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path fileStorageLocation = Paths.get(applicationProperties.getUploadir()).toAbsolutePath().normalize();
            Files.createDirectories(fileStorageLocation);
            Path targetLocation  = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileStorageLocation.toString().concat("/").concat(fileName);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

}
