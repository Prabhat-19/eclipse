package com.payearly.web.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payearly.dto.EntityCreationDTO;
import com.payearly.dto.GstnResponseDTO;
import com.payearly.model.Process_Log;
import com.payearly.repository.EntityCreationRepository;
import com.payearly.service.EntityCreationService;
import com.payearly.web.rest.errors.EntityExceptionHandler;
import com.payearly.web.rest.errors.EntityNotFondException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EntityCreationResource {
	
	@Autowired
	Process_Log log;
	
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
	public void createEntity( @RequestBody EntityCreationDTO entityCreationDTO) throws IOException, JSONException, EntityNotFondException {
		
		if (db.findByentityPAN(entityCreationDTO.getGstin().substring(2, 12)).isPresent()) {
		//throw new IllegalArgumentException("this pan is already persen t in gthe d");
		throw new EntityNotFondException("PAN card already present");
		}
		try
		{
		GstnResponseDTO gstnResponseDTO  = entityCreationService.getGstnResponse(entityCreationDTO);
		
		
		//System.out.println(gstnResponseDTO.toString());
		JSONObject obj = new JSONObject(gstnResponseDTO);
		
		Map<Object, Object> m = new LinkedHashMap<Object, Object>();
		m.put("gstin", obj.getString("gstin"));
		m.put("entityName", obj.getString("entityName"));
		m.put("entityPAN", obj.getString("entityPAN"));
		m.put("entityRegisteredAddress", obj.getString("entityRegisteredAddress"));
		m.put("entityState", obj.get("entityState"));
		m.put("entityPIN", obj.getString("entityPIN"));
		m.put("entityConstitution", obj.getString("entityConstitution"));
		m.put("Status", "");
		HttpEntity<Map<Object, Object>> entity1 = new HttpEntity(m);
		String uri = "http://localhost:8081/api/jbpm";
		
		System.out.println("I am inside createEntity()");
//		rst.exchange (uri, HttpMethod.POST, entity1, String.class);
		
		sendData(m);
		}
		catch(Exception e)
		{
			System.out.println("Catching the exception");
			
		}
}
	
//	@PostMapping(value = "/jbpm", consumes = "application/json", produces = "application/json")
	private void sendData(Map<Object, Object> m)
	{
		//System.out.println("Printing in sendData()"+ entityCreationService.sendDataToJbpm(m));
		String data =entityCreationService.sendDataToJbpm(m);
		 
		
	
		 
			 try {
				JSONObject json = new JSONObject(data);
	
				log.setEntityName(json.getString("entityName"));
				log.setEntityPAN(json.getString("entityPAN"));
				log.setEntityState(json.getString("entityState"));
				log.setGstin(json.getString("gstin"));
				log.setParent_Instance_Id(json.getInt("parent-instance-id"));
				log.setProcess_Id(json.getString("Process_id"));
				log.setProcess_Instance_Id(json.getInt("process-instance-id"));
				log.setStatus("NOT LIVE");
				
				db.save(log);
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Not able to found appropiate data");
			}
		 
}


	@GetMapping(path="/get_data")
	public List<Process_Log> readJson()
	{
		System.out.println("Getting data from db");
		
		
		return db.findAll();
	}
		
		
				
		
	
}
