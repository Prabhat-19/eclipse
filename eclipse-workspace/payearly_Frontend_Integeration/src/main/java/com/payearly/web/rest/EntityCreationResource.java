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
import com.payearly.dto.EntityNameDto;
import com.payearly.dto.GstnResponseDTO;
import com.payearly.model.EntityDetails;
import com.payearly.model.ProcessLog;
import com.payearly.repository.EntityCreationRepository;
import com.payearly.service.EntityCreationService;
import com.payearly.web.rest.errors.EntityNotFondException;
import com.payearly.web.rest.errors.GstinNotFoundException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://192.168.1.13:4200")

public class EntityCreationResource {
	
	@Autowired
	ProcessLog log;
	
	@Autowired
	EntityCreationRepository db;

	@Autowired
	RestTemplate rst;
	
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
	

	@GetMapping(path="/entity-names")
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
	
	@PostMapping(path="/entity-name")
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
	
	
	@DeleteMapping(path="/entitity-detail/{gstin}")
	public void delete(@PathVariable String gstin){
	
		
		entityCreationService.deleteEntityDetail(gstin);
		
		
	}	
}
