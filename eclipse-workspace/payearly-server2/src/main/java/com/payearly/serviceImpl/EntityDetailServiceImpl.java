package com.payearly.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.payearly.config.ApplicationProperties;
import com.payearly.domain.AdditionalDetail;
import com.payearly.domain.Address;
import com.payearly.domain.BankDetail;
import com.payearly.domain.EntityDetail;
import com.payearly.domain.EntityDocuments;
import com.payearly.domain.EntityMapping;
import com.payearly.domain.ProcessLog;
import com.payearly.enums.AddressType;
import com.payearly.enums.DeletionType;
import com.payearly.repository.AdditionalDetailRepository;
import com.payearly.repository.AddressRepository;
import com.payearly.repository.BankDetailRepository;
import com.payearly.repository.EntityDetailRepository;
import com.payearly.repository.EntityDocumentsRepository;
import com.payearly.repository.EntityMappingRepository;
import com.payearly.repository.ProcessLogRepository;
import com.payearly.service.EntityDetailService;
import com.payearly.service.dto.EntityAndAddressDetailDTO;
import com.payearly.service.dto.EntityDetailDTO;
import com.payearly.service.dto.EntityMappingDTO;
import com.payearly.service.dto.EntityNameDto;


@Service
@Transactional
public class EntityDetailServiceImpl implements EntityDetailService {

    private final EntityDetailRepository  entityDetailsReposoitory;
    private final AddressRepository       entityAddressRepository;
    private final EntityMappingRepository entityMappingRepository;
    private final ProcessLogRepository    processLogRepository;
    private final BankDetailRepository bankDetailRepository;
    private final AdditionalDetailRepository additionalDetailRepository;
    private final EntityDocumentsRepository EntityDocumentsRepository;
    private final ApplicationProperties applicationProperties;

    public EntityDetailServiceImpl(EntityDetailRepository entityDetailsReposoitory,
            AddressRepository entityAddressRepository, EntityMappingRepository entityMappingRepository,
            ProcessLogRepository processLogRepository, BankDetailRepository bankDetailRepository,
            AdditionalDetailRepository additionalDetailRepository,
            com.payearly.repository.EntityDocumentsRepository entityDocumentsRepository,
            ApplicationProperties applicationProperties) {
        super();
        this.entityDetailsReposoitory = entityDetailsReposoitory;
        this.entityAddressRepository = entityAddressRepository;
        this.entityMappingRepository = entityMappingRepository;
        this.processLogRepository = processLogRepository;
        this.bankDetailRepository = bankDetailRepository;
        this.additionalDetailRepository = additionalDetailRepository;
        EntityDocumentsRepository = entityDocumentsRepository;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public EntityAndAddressDetailDTO getEntityDetail(String id) {
        EntityAndAddressDetailDTO addressDetailDTO = new EntityAndAddressDetailDTO();
        
        Optional<EntityDetail> entityDetails = entityDetailsReposoitory.findBygstin(id);
        List<Address> entityAddresses = entityAddressRepository.findAllByEntityDetail_id(entityDetails.get().getId());
        addressDetailDTO.setDetails(entityDetails.get());
        addressDetailDTO.setAddresses(entityAddresses);
        return addressDetailDTO;
    }

    @Override
    public EntityDetail getEntityDetail(EntityAndAddressDetailDTO entityDetails) {
        Optional<EntityDetail> details = entityDetailsReposoitory.findById(entityDetails.getDetails().getId());

        details.get().setConstitution(entityDetails.getDetails().getConstitution());
        // details.get().setConstitution(entityDetails.getDetails().getConstitution());
        details.get().setEntityPan(entityDetails.getDetails().getEntityPan());
        details.get().setEntityName(entityDetails.getDetails().getEntityName());
        details.get().setEntityWebsite(entityDetails.getDetails().getEntityWebsite());
        // details.get().setEntityTan(entityDetails.getDetails().getEntityTan());
        EntityDetail entityDetail = entityDetailsReposoitory.save(details.get());
        entityDetails.getAddresses().stream().forEach(address -> {
            Optional<Address> entityAddress = entityAddressRepository.findById(address.getId());
            entityAddress.get().setBuildingName(address.getBuildingName());
            entityAddress.get().setBuildingNo(address.getBuildingNo());
            entityAddress.get().setCity(address.getCity());
            entityAddress.get().setDistrict(address.getDistrict());
            entityAddress.get().setFlateNo(address.getFlateNo());
            entityAddress.get().setEntityDetail(entityDetail);
            entityAddress.get().setLocation(address.getLocation());
            entityAddress.get().setPinCode(address.getPinCode());
            entityAddress.get().setState(address.getState());
            entityAddressRepository.save(entityAddress.get());
        });

        return entityDetail;
    }

    @Override
    public void deleteEntityDetail(UUID gstnId) {
    	Optional<EntityDetail> entityDetails = entityDetailsReposoitory.findById(gstnId);
    	entityDetails.get().setDeletionType(DeletionType.DELETED);
    	
    	entityDetailsReposoitory.save(entityDetails.get());
    }

    @Override
    public EntityMapping createEntityDetail(EntityMappingDTO entityAndAddressDetailDTO) {
        EntityDetail entityDetails = entityDetailsReposoitory
                .findByentityName(entityAndAddressDetailDTO.getEntityName());
        EntityMapping entityMapping = new EntityMapping();
        // entityMapping.setProduct(entityAndAddressDetailDTO.getEntityMappingName());
        entityMapping.setEntityDetail(entityDetails);
        entityMapping.setEntityid(entityAndAddressDetailDTO.getEntityid());
        entityMappingRepository.save(entityMapping);

        return entityMapping;
    }

    @Override
    public List<EntityMapping> getEntityMapping(String entityName) {

        return entityMappingRepository.findByentityDetail_entityName(entityName);
    }

    @Override
    public List<EntityNameDto> getAllEntity() {
        
        List<EntityNameDto> listEntityName = new ArrayList<EntityNameDto>();
        
        List<Object[]> list = entityDetailsReposoitory.findAllEntityname();
        for (Object[] obj : list) {
            EntityNameDto ent = new EntityNameDto();
            ent.setId(obj[0].toString());
            ent.setEntityName(obj[1].toString());
            listEntityName.add(ent);
        }
        return listEntityName;
    }

    @Override
    public List<EntityDetailDTO> getAllEntityAndAddress() {
        
        List<EntityDetailDTO>  entityDetailDTO = entityDetailsReposoitory.findAll().stream().map(entity -> {
            EntityDetailDTO detailDTO = new EntityDetailDTO();
            detailDTO.setEntityName(entity.getEntityName());
            detailDTO.setEntityPan(entity.getEntityPan());
            detailDTO.setFlag(entity.getFlag());
            detailDTO.setGstin(entity.getGstin());
            detailDTO.setId(entity.getId());
            detailDTO.setConstitution(entity.getConstitution());
            Address address = entityAddressRepository.findAllByEntityDetail_idAndAddressType(entity.getId(), AddressType.REGISTERED_ADDRESS);
            detailDTO.setLocation(address.getLocation());
             return detailDTO;
         }).collect(Collectors.toList());

        return entityDetailDTO;
    }

    @Override
    public BankDetail createBankDetail(@Valid BankDetail bankDetail) {

        return bankDetailRepository.save(bankDetail);
    }

    @Override
    public String uploadDocument(String name, @Valid MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Integer integer = fileName.indexOf(".");
        fileName = fileName.substring(integer);
        String instant = Instant.now().toString();
        fileName = name.concat("-").concat(instant).concat(fileName);
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

    @Override
    public EntityDetail createEntityDetail(@Valid EntityDetail entityDetail) {

        return entityDetailsReposoitory.save(entityDetail);
    }

    @Override
    public EntityDetail updateEntityDetail(@Valid EntityDetail entityDetail) {
    
        return entityDetailsReposoitory.save(entityDetail);
    }

    @Override
    public Page<EntityDetail> getEntityDetail(Pageable pageable) {
       
    	return entityDetailsReposoitory.findBydeletionType(pageable, DeletionType.DELETED);
    }

    @Override
    public EntityDetail getEntityDetails(UUID id) {

        return entityDetailsReposoitory.findById(id).get();
    }

    @Override
    public EntityAndAddressDetailDTO getEntityDetail(UUID entityDetailId) {

        EntityAndAddressDetailDTO addressDetailDTO = new EntityAndAddressDetailDTO();
        Optional<EntityDetail> entityDetails = entityDetailsReposoitory.findById(entityDetailId);
        List<Address> entityAddresses = entityAddressRepository.findAllByEntityDetail_idOrderByAddressTypeDesc(entityDetails.get().getId());
        List<BankDetail> bankDetails = bankDetailRepository.findAllByEntityDetail_id(entityDetails.get().getId());
        List<AdditionalDetail> additionalDetails = additionalDetailRepository
                .findAllByEntityDetail_id(entityDetails.get().getId());
        List<EntityDocuments> entityDocuments = EntityDocumentsRepository
                .findAllByEntityDetail_id(entityDetails.get().getId());
        addressDetailDTO.setDetails(entityDetails.get());
        addressDetailDTO.setAddresses(entityAddresses);
        addressDetailDTO.setBankDetails(bankDetails);
        addressDetailDTO.setEntityDocuments(entityDocuments);
        addressDetailDTO.setAdditionalDetails(additionalDetails);

        return addressDetailDTO;
    }
}
