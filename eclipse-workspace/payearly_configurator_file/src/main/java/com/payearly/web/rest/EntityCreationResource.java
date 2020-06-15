package com.payearly.web.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.payearly.dto.EntityAndAddressDetailDTO;
import com.payearly.dto.EntityCreationDTO;
import com.payearly.dto.EntityMappingDTO;
import com.payearly.dto.EntityNameDto;
import com.payearly.dto.GstnResponseDTO;
import com.payearly.model.BankDetail;
import com.payearly.model.EntityDetails;
import com.payearly.model.EntityMapping;
import com.payearly.model.ProcessLog;
import com.payearly.repository.EntityCreationRepository;
import com.payearly.repository.EntityDetailsReposoitory;
import com.payearly.service.EntityCreationService;
import com.payearly.web.rest.errors.EntityNotFondException;
import com.payearly.web.rest.errors.GstinNotFoundException;


@RestController
@RequestMapping("/api")
//@CrossOrigin(origins="http://localhost:4200")
public class EntityCreationResource {
	
	@Autowired
	ProcessLog log;
	
	@Autowired
	EntityCreationRepository db;

	@Autowired
	RestTemplate rst;
	
	@Autowired
	EntityDetailsReposoitory entitydetailsRepo;
	
	private final EntityCreationService entityCreationService;

	public EntityCreationResource(EntityCreationService entityCreationService) {
		super();
		this.entityCreationService = entityCreationService;
	}
	

	
	@PostMapping("/create")
	public void createEntity( @RequestBody EntityCreationDTO entityCreationDTO) throws Exception {
		GstnResponseDTO gstnResponseDTO ;
		
		gstnResponseDTO  = entityCreationService.getGstnResponse(entityCreationDTO);
		
		
	
		JSONObject obj = new JSONObject(gstnResponseDTO);
		
		Map<Object, Object> m = new LinkedHashMap<Object, Object>();
		m.put("gstin", obj.getString("gstin").toString());
		m.put("entityName", obj.getString("entityName").toString());
		m.put("entityPAN", obj.getString("entityPAN").toString());
		m.put("entityRegisteredAddress", obj.getString("entityRegisteredAddress").toString());
		m.put("entityState", obj.get("entityState").toString());
		m.put("entityPIN", obj.getString("entityPIN"));
		m.put("entityConstitution", obj.getString("entityConstitution").toString());
		m.put("Status","Not Live");
		m.put("flag", obj.getString("flag"));
		
		///String uri = "http://localhost:8081/api/jbpm";
		
		System.out.println("I am inside createEntity()");
		
		entityCreationService.sendData(m);
		
}
	

	
	@GetMapping(path="/get_data")
	public List<ProcessLog> readJson() throws EntityNotFondException
	{
		
		
		try
		{
		//return db.findAll();
		return db.findAllOrderBycreatedDateDesc();
		}
		catch(Exception e)
		{
			throw new EntityNotFondException("Not able to fetch details from database" );
		}
	}
	
	// getting both entity and corresponding address details
			@GetMapping("/list_gstin")
			public List<EntityAndAddressDetailDTO> listGstin()
			{
				System.out.println("inside list_gstin");
				List<Object> gstinList = entitydetailsRepo.findAllEntitygstin();
				List<EntityAndAddressDetailDTO> getEntityData = new ArrayList<EntityAndAddressDetailDTO>();
				for(Object o : gstinList)
				{
					System.out.println("gstin number on parsing is "+ o.toString());
					getEntityData.add( entityCreationService.getEntityDetail(o.toString()));
				}
				return getEntityData;
			}
		
		
	
//Source entity
	@GetMapping(path="/entity-source")
	public List<EntityNameDto> getAllEntityName(){
		List<EntityNameDto> listEntityName = new ArrayList<EntityNameDto>();
		List<Object[]> list = db.findAllEntityname();
		for (Object[] obj : list) {
			
			EntityNameDto ent = new EntityNameDto();
			ent.setEntityName(obj[0].toString());
			listEntityName.add(ent);
		}
		return listEntityName;
		
	}
	//Target entity
	@PostMapping(path="/entity-target")
	public List<EntityNameDto> getAllEntityNames(@RequestBody EntityNameDto entity ){
		List<EntityNameDto> listEntityName = new ArrayList<EntityNameDto>();
		List<Object[]> list = db.findAllByentityName(entity.getEntityName());
		for (Object[] obj : list) {
			
			EntityNameDto ent = new EntityNameDto();
			ent.setEntityName(obj[0].toString());
			listEntityName.add(ent);
		}
		return listEntityName;
		
	} 
	
	@GetMapping(path="/entitity-detail/{id}")
	public EntityAndAddressDetailDTO getAllEntityName(@PathVariable String id){
		
		return entityCreationService.getEntityDetail(id);
		
	}			
	
	@PutMapping(path="/entitity-detail")
	public EntityDetails updateEntityDetail(@RequestBody EntityAndAddressDetailDTO entityAndAddressDetailDTO){
		
		return entityCreationService.getEntityDetail(entityAndAddressDetailDTO);
		
	}	
	
	
	@DeleteMapping("/entitity-detail-delete/{gstin}") //changed the name
	public void delete(@PathVariable String gstin){
	
		
		entityCreationService.deleteEntityDetail(gstin);
		
		
	}	
	
	@PostMapping("/entity-mapping")
	public EntityMapping createEntityDetail(@RequestBody EntityMappingDTO entityAndAddressDetailDTO){
		
		return entityCreationService.createEntityDetail(entityAndAddressDetailDTO);
		
	}
	
	@PostMapping("/get-entity-mappings" )
	public List<EntityMapping> getAllEntityMapping(@RequestBody EntityNameDto entityname){
		System.out.println("vbjhbhjbjhbjhb" + entityname);
		
		return entityCreationService.getEntityMapping(entityname.getEntityName());
		
	}
	
	@PostMapping("/save-bank-details")
	public BankDetail saveBankDetails(@RequestBody BankDetail bankDetail)
	{
		
		
		return entityCreationService.saveBankDetails(bankDetail);
				
	}
	
	@GetMapping(path="/get-bank-details/{id}")
	public List<BankDetail> getBankDetails(@PathVariable String id)
	{
		return entityCreationService.getBankDetailsById(id);
		
	}
	
	@DeleteMapping(path="/delete-bank-details/{id}")
	public void deleteBankDetails(@PathVariable Long id)
	{
		System.out.println("inside delete api");
		entityCreationService.deleteBankDetailsByAccNo(id);
		
	}
	
	
	
	
	
}
