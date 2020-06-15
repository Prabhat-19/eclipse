package com.payearly.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static java.util.Objects.*;

import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.payearly.domain.Address;
import com.payearly.domain.EntityDetail;
import com.payearly.domain.ProcessLog;
import com.payearly.enums.AddressType;
import com.payearly.repository.AddressRepository;
import com.payearly.repository.EntityDetailRepository;
import com.payearly.repository.ProcessLogRepository;
import com.payearly.service.ProcessLogService;
import com.payearly.service.dto.EntitiyAddressDTO;
import com.payearly.service.dto.EntityCreationDTO;
import com.payearly.service.dto.GstnResponseDTO;
import com.payearly.service.exapi.GstnApiClientService;
import com.payearly.service.exapi.JbpmApiClientService;

@Service
@Transactional
public class ProcessLogServiceImpl  implements ProcessLogService{

    private final Logger log = LoggerFactory.getLogger(ProcessLogServiceImpl.class);
    
    private final ProcessLogRepository processLogRepository;
    
    private final GstnApiClientService gstnApiClientService;

    private final JbpmApiClientService jbpmApiClientService;
    
    private final EntityDetailRepository entityDetailRepository;
    
    private final AddressRepository addressRepository;

    public ProcessLogServiceImpl(ProcessLogRepository processLogRepository, GstnApiClientService gstnApiClientService,
            JbpmApiClientService jbpmApiClientService, EntityDetailRepository entityDetailRepository,
            AddressRepository addressRepository) {
        super();
        this.processLogRepository = processLogRepository;
        this.gstnApiClientService = gstnApiClientService;
        this.jbpmApiClientService = jbpmApiClientService;
        this.entityDetailRepository = entityDetailRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public ProcessLog save(ProcessLog processLog) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProcessLog findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProcessLog findById(UUID id) {
        // TODO Auto-generated method stub     
        return null;
    }

    @Override
    public void delete(UUID id) {
        // TODO Auto-generated method stub
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public ProcessLog createProcess(@Valid EntityCreationDTO entityCreationDTO) throws IOException {
        log.info(" request to create the ProcessLog to the  corresponding  gstn : {}", entityCreationDTO.getGstin());
        GstnResponseDTO gstnResponseDTO = gstnResponse(entityCreationDTO);

        return sendGstnResponseDataToJbpm(gstnResponseDTO);
    }

    @SuppressWarnings("unchecked")
    private GstnResponseDTO gstnResponse(EntityCreationDTO entityCreationDTO) {
        ResponseEntity<JSONObject> gstnResponse;

        GstnResponseDTO gstnResponseDTO = new GstnResponseDTO();
       try {
           gstnResponse  = gstnApiClientService.getGstenApiResponse(entityCreationDTO.getGstin());
           //gstnResponseDTO.setStatusCode(gstnResponse.getStatusCode().value());
           log.info("REST request to get gstn response : {}", entityCreationDTO.getGstin());
        } catch (HttpStatusCodeException codeException) {
            EntityDetail entityDetails = new EntityDetail();

            log.info("REST request has't proper response on gstin = : {} ", entityCreationDTO.getGstin());
            entityDetails.setGstin(entityCreationDTO.getGstin());
            entityDetails.setEntityName(entityCreationDTO.getEntityName());
            entityDetails.setEntityPan(entityCreationDTO.getGstin().substring(2, 12));

            if (codeException.getStatusCode().value() == 404) {
               gstnResponseDTO.setStatusCode(codeException.getStatusCode().value());
               entityDetails.setFlag(codeException.getStatusCode().value());
               entityDetailRepository.save(entityDetails);
                 return gstnResponseDTO;
            } else {
                gstnResponseDTO.setStatusCode(codeException.getStatusCode().value());
                entityDetails.setFlag(codeException.getStatusCode().value());

                entityDetailRepository.save(entityDetails);
                return gstnResponseDTO;
            }
        }
            return dummyMethod(entityCreationDTO, gstnResponse, gstnResponseDTO);
    }

    @SuppressWarnings("unchecked")
    private GstnResponseDTO dummyMethod(EntityCreationDTO entityCreationDTO, ResponseEntity<JSONObject> gstnResponse, GstnResponseDTO gstnResponseDTO) {
        ObjectMapper objectMapper = new ObjectMapper(); 
        JsonNode gstnResponseRoot = null;
        try {
            gstnResponseRoot = objectMapper.readTree(gstnResponse.getBody().toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
         EntityDetail entityDetail = new EntityDetail();

         entityDetail.setConstitution(gstnResponseRoot.get("ctb").asText());

         //entityDetail.setEntityTin(gstnResponseRoot.get("tin"));
         entityDetail.setEntityName(gstnResponseRoot.get("lgnm").textValue());
         entityDetail.setGstin(gstnResponseRoot.get("gstin").textValue());
         entityDetail.setEntityPan(entityDetail.getGstin().substring(2, 12));
         //entityDetail.setEntityWebsite(gstnResponseRoot.findValue("web").textValue());
        // entityDetail.setNatureOfBusiness(gstnResponseRoot.get("nbr").textValue());
         entityDetail = entityDetailRepository.save(entityDetail);
         entityDetail.setFlag(200);

         JsonNode registeredAddress= gstnResponseRoot.get("pradr").get("addr");

         List<Address> addresses = new ArrayList<Address>();
          
          if (nonNull(registeredAddress)) {
              Address address = new Address();
              address.setBuildingName(registeredAddress.findValue("bnm").asText());
              address.setLocation(registeredAddress.findValue("loc").asText());
              address.setStreet(registeredAddress.findValue("st").textValue());
              address.setBuildingNo(registeredAddress.findValue("bno").textValue());
              address.setState(registeredAddress.findValue("stcd").textValue());
              address.setDistrict(registeredAddress.findValue("dst").textValue());
              address.setCity(registeredAddress.findValue("city").textValue());
              address.setFlateNo(registeredAddress.findValue("flno").textValue());
              address.setPinCode(registeredAddress.findValue("pncd").textValue());
              address.setAddressType(AddressType.REGISTERED_ADDRESS);
              address.setEntityDetail(entityDetail);
              addressRepository.save(address);

              addresses.add(address);
          }
          JsonNode secondaryAddress  = gstnResponseRoot.findValue("adadr");
          if (nonNull(secondaryAddress)) {
              JSONArray addressArray = new JSONArray();
              addressArray.addAll(secondaryAddress.findValues("addr"));

              for (Object object : addressArray) {
                  Address address = new Address();
                  JsonNode jsonNode= null;
               try {
                   jsonNode = objectMapper.readTree(object.toString());
               } catch (IOException e) {
                   e.printStackTrace();
               }
               address.setBuildingName(jsonNode.findValue("bnm").asText());
               address.setLocation(jsonNode.findValue("loc").asText());
               address.setStreet(jsonNode.findValue("st").textValue());
               address.setBuildingNo(jsonNode.findValue("bno").textValue());
               address.setState(jsonNode.findValue("stcd").textValue());
               address.setDistrict(jsonNode.findValue("dst").textValue());
               address.setCity(jsonNode.findValue("city").textValue());
               address.setFlateNo(jsonNode.findValue("flno").textValue());
               address.setPinCode(jsonNode.findValue("pncd").textValue());
               address.setAddressType(AddressType.OPERATIONAL_ADDRESS);
               address.setEntityDetail(entityDetail);
               addressRepository.save(address);
               addresses.add(address);
          }}

        gstnResponseDTO.setEntityDetail(entityDetail);
        gstnResponseDTO.setEntityOperatingAddress(addresses);
      return gstnResponseDTO;
    }

    private ProcessLog sendGstnResponseDataToJbpm(GstnResponseDTO gstnResponseDTO) throws IOException {
       log.info("data sending to the jbpm ");
       String processIntanceId = jbpmApiClientService.jbpmStartApi(gstnResponseDTO).getBody().toString();
       String processInfo = jbpmApiClientService.getJbpmTaskInstance(Integer.valueOf(processIntanceId)).getBody().toString();
       ObjectMapper objectMapper = new XmlMapper();
       JsonNode jsonNode = objectMapper.readTree(processInfo.getBytes());
       Integer taskId =  Integer.valueOf(jsonNode.findValue("task-id").textValue());
       jbpmApiClientService.startJbpmTaskOne(taskId);
       gstnResponseDTO.setParentInstanceId(Integer.valueOf(jsonNode.findValue("parent-instance-id").textValue()));
       gstnResponseDTO.setProcessId(jsonNode.findValue("process-id").textValue());
       gstnResponseDTO.setProcessInstanceId(Integer.valueOf(jsonNode.findValue("process-instance-id").textValue()));
       jbpmApiClientService.compleatationOfTaskOne(taskId, gstnResponseDTO);
       String taskOutput = jbpmApiClientService.taskOneOutput(taskId).getBody().toString();
      // jsonNode = objectMapper.readTree(taskOutput.getBytes());
       ProcessLog processLog = new ProcessLog();
       processLog.setEntityDetail(gstnResponseDTO.getEntityDetail());
       processLog.setParentInstanceId(Integer.valueOf(jsonNode.findValue("parent-instance-id").textValue()));
       processLog.setProcessId(jsonNode.findValue("process-id").textValue());
       processLog.setProcessInstanceId(Integer.valueOf(jsonNode.findValue("process-instance-id").textValue()));
       
       return processLogRepository.save(processLog);
    }
   
    

}