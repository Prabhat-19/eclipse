package com.payearly.service;

import java.io.IOException;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import com.payearly.domain.ProcessLog;
import com.payearly.service.dto.EntityCreationDTO;

public interface ProcessLogService {

    ProcessLog save(ProcessLog processLog);
    
    ProcessLog findAll(Pageable pageable);
    
    ProcessLog findById(UUID id);
    
    void delete(UUID id);

    ProcessLog createProcess(@Valid EntityCreationDTO entityCreationDTO) throws IOException;
    
}
