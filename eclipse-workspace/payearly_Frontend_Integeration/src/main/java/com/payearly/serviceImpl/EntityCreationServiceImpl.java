package com.payearly.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payearly.dto.EntitiyAddressDTO;
import com.payearly.dto.EntityAndAddressDetailDTO;
import com.payearly.dto.EntityCreationDTO;
import com.payearly.dto.GstnResponseDTO;
import com.payearly.model.EntityAddress;
import com.payearly.model.EntityDetails;
import com.payearly.model.ProcessLog;
import com.payearly.repository.EntityAddressRepository;
import com.payearly.repository.EntityCreationRepository;
import com.payearly.repository.EntityDetailsReposoitory;
import com.payearly.service.EntityCreationService;
import com.payearly.utills.UtillsClass;
import com.payearly.web.rest.errors.EntityNotFondException;
import com.payearly.web.rest.errors.GstinNotFoundException;

@Service
public class EntityCreationServiceImpl implements EntityCreationService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UtillsClass ut;
	
	@Autowired
	ProcessLog log;
	
	@Autowired
	private EntityDetailsReposoitory entityDetailsReposoitory;
	
	@Autowired
	private EntityAddressRepository entityAddressRepository;
	
	
	@Autowired
	EntityCreationRepository db;

	@Autowired
	private EntityCreationRepository entityCreationRepository;
	
	@Override
	public GstnResponseDTO getGstnResponse(EntityCreationDTO entityCreationDTO) throws IOException, JSONException, EntityNotFondException {

		
		
		if (db.findByentityPAN(entityCreationDTO.getGstin().substring(2, 12)).isPresent()) {
				//throw new IllegalArgumentException("this pan is already persen t in gthe d");
				throw new EntityNotFondException("PAN card already present");
				}
		
		String baseUri = "https://api.taxprogsp.co.in/commonapi/v1.1/search?aspid=1625311919&password=15Aug1947@&action=TP&Gstin="+entityCreationDTO.getGstin();

		HttpHeaders headers =new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> reqentity = new HttpEntity<>(headers);
		GstnResponseDTO gstnResponseDTO = new GstnResponseDTO();
	     gstnResponseDTO.setGstin(entityCreationDTO.getGstin());
		
//	     ResponseEntity<JSONObject> response = null;
//    try {
//    
//    	 response =restTemplate.exchange(baseUri,HttpMethod.GET,reqentity, JSONObject.class, HttpStatus.NOT_FOUND, HttpStatus.OK, HttpStatus.BAD_REQUEST);
//		
//     } catch (HttpStatusCodeException exception ) {
//    	 if (exception.getStatusCode().value()==400) {
//			gstnResponseDTO.setFlag(exception.getStatusCode().value());
//			return gstnResponseDTO;
//		}else {
//			gstnResponseDTO.setFlag(exception.getStatusCode().value());
//			return gstnResponseDTO;
//		}
//     }
    ResponseEntity<String> response = null;
try {

	 response =restTemplate.exchange(baseUri,HttpMethod.GET,reqentity, String.class);
	 gstnResponseDTO.setFlag(response.getStatusCode().value());
	
} catch (HttpStatusCodeException exception ) {
	 if (exception.getStatusCode().value()==400) {
		// System.out.println("Bad request" + exception.getStatusCode().value());
		gstnResponseDTO.setFlag(exception.getStatusCode().value());
		return gstnResponseDTO;
	}else {
		gstnResponseDTO.setFlag(exception.getStatusCode().value());
		return gstnResponseDTO;
	}
}
		JSONObject obj = new JSONObject(response);
		System.out.println(">>>>>>>>>>>>"+ obj);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(response.getBody().toString());

		
		gstnResponseDTO.setEntityName(root.findValue("lgnm").toString().replace("\"", ""));
		gstnResponseDTO.setGstin(entityCreationDTO.getGstin());
		gstnResponseDTO.setEntityPAN(entityCreationDTO.getGstin().substring(2,12));
		gstnResponseDTO.setEntityPIN(root.findValue("pncd").toString());
		gstnResponseDTO.setEntityState(root.findValue("stcd").toString().replace("\"", ""));
		gstnResponseDTO.setEntityConstitution(root.findValue("ctb").toString());

		EntitiyAddressDTO pr_add = new EntitiyAddressDTO();
		pr_add.setBnm(root.findValue("pradr").findValue("addr").findValue("bnm").toString());
		pr_add.setLoc(root.findValue("pradr").findValue("addr").findValue("loc").toString());
		pr_add.setSt(root.findValue("pradr").findValue("addr").findValue("st").toString());
		pr_add.setBno(root.findValue("pradr").findValue("addr").findValue("bno").toString());
		pr_add.setStcd(root.findValue("pradr").findValue("addr").findValue("stcd").toString());
		pr_add.setDst(root.findValue("pradr").findValue("addr").findValue("dst").toString());
		pr_add.setCity(root.findValue("pradr").findValue("addr").findValue("city").toString());
		pr_add.setFlno(root.findValue("pradr").findValue("addr").findValue("flno").toString());
		pr_add.setPncd(root.findValue("pradr").findValue("addr").findValue("pncd").toString());
		pr_add.setIsPrimary(true);
		
		gstnResponseDTO.setEntityRegisteredAddress(pr_add);
		
		List<EntitiyAddressDTO> addArry = new ArrayList<EntitiyAddressDTO>();
		
		if(root.findValue("adadr") != null)
		{

			JSONArray arry1 = new JSONArray(root.findValue("adadr").findValues("addr"));
			


			for (int i = 0; i < arry1.length(); ++i) {	
				String str_add = arry1.getString(i);
				JsonNode rec_add = objectMapper.readTree(str_add);

				EntitiyAddressDTO e_add = new EntitiyAddressDTO();
				e_add.setBnm(rec_add.findValue("bnm").toString());
				e_add.setLoc(rec_add.findValue("loc").toString());
				e_add.setSt(rec_add.findValue("st").toString());
				e_add.setBno(rec_add.findValue("bno").toString());
				e_add.setStcd(rec_add.findValue("stcd").toString());
				e_add.setDst(rec_add.findValue("dst").toString());
				e_add.setCity(rec_add.findValue("city").toString());
				e_add.setFlno(rec_add.findValue("flno").toString()); 
				e_add.setPncd(rec_add.findValue("pncd").toString());
				e_add.setLoc( rec_add.findValue("loc").toString());
                e_add.setIsPrimary(false);
				addArry.add(e_add);
			}
		}
		addArry.add(pr_add);
		
       EntityDetails entityDetails = new EntityDetails();
   	
		 entityDetails.setGstin(gstnResponseDTO.getGstin());
		 entityDetails.setEntityName(gstnResponseDTO.getEntityName());
		 entityDetails.setEntityPan(gstnResponseDTO.getEntityPAN());
		 entityDetails.setConstitution(gstnResponseDTO.getEntityConstitution());
		 
		
		 EntityDetails details = entityDetailsReposoitory.save(entityDetails);
		 
		gstnResponseDTO.setEntityOperatingAddress(addArry);
	  List<EntityAddress> entityAddresss = new ArrayList<EntityAddress>();
	  
		if (gstnResponseDTO.getEntityRegisteredAddress() != null) { //change
			EntityAddress entityAddress = new EntityAddress();
			
			entityAddress.setBuildingName(gstnResponseDTO.getEntityRegisteredAddress().getBnm());
			entityAddress.setBuildingNo(gstnResponseDTO.getEntityRegisteredAddress().getBno());
			entityAddress.setCity(gstnResponseDTO.getEntityRegisteredAddress().getCity());
			entityAddress.setDistrict(gstnResponseDTO.getEntityRegisteredAddress().getDst());
			entityAddress.setFlateNo(gstnResponseDTO.getEntityRegisteredAddress().getFlno());
			entityAddress.setIsPrimary(true);
			entityAddress.setPincode(gstnResponseDTO.getEntityRegisteredAddress().getPncd());
			entityAddress.setEntityDetails(entityDetails);
			entityAddress.setState(gstnResponseDTO.getEntityRegisteredAddress().getStcd()); //change
			entityAddress.setStreet(gstnResponseDTO.getEntityRegisteredAddress().getSt()); //change
			entityAddress.setLocation(gstnResponseDTO.getEntityRegisteredAddress().getLoc()); //change
			
			
			
			entityAddresss.add(entityAddress);
		} 
		if (gstnResponseDTO.getEntityOperatingAddress() != null && gstnResponseDTO.getEntityOperatingAddress().size() > 1) {
			
		for (EntitiyAddressDTO entitiyAddressDTO : gstnResponseDTO.getEntityOperatingAddress()) {
			if (entitiyAddressDTO.getIsPrimary()== false) {
				EntityAddress entityAddress = new EntityAddress();
				entityAddress.setBuildingName(entitiyAddressDTO.getBnm());
				entityAddress.setBuildingNo(entitiyAddressDTO.getBno());
				entityAddress.setCity(entitiyAddressDTO.getCity());
				entityAddress.setDistrict(entitiyAddressDTO.getDst());
				entityAddress.setFlateNo(entitiyAddressDTO.getFlno());
				entityAddress.setIsPrimary(false);
				entityAddress.setEntityDetails(entityDetails);
				entityAddress.setState(entitiyAddressDTO.getStcd()); //change
				entityAddress.setStreet(entitiyAddressDTO.getSt()); //change
				entityAddress.setLocation(entitiyAddressDTO.getLoc()); //change
				
				entityAddress.setPincode(entitiyAddressDTO.getPncd());
				entityAddresss.add(entityAddress);
			
			}
				
		}
		}
		
		entityAddresss.stream().forEach(m -> {
			entityAddressRepository.save(m);
		});
        
		 
		return gstnResponseDTO;
		
		}

	
	@Override
	public void sendData(Map<Object, Object> m) throws Exception
	{
		//System.out.println("Printing in sendData()"+ entityCreationService.sendDataToJbpm(m));
		String data =sendDataToJbpm(m);
		System.out.println("output is" +data);
		 
		
	
		 
			 try {
				JSONObject json = new JSONObject(data);
	
				log.setEntityName(json.getString("entityName").replaceAll("null", ""));
				log.setEntityPAN(json.getString("entityPAN").replaceAll("null", ""));
				log.setEntityState(json.getString("entityState").replaceAll("null", ""));
				log.setGstin(json.getString("gstin"));
				log.setParent_Instance_Id(json.getInt("parent-instance-id"));
				log.setProcess_Id(json.getString("Process_id"));
				log.setProcess_Instance_Id(json.getInt("process-instance-id"));
				log.setStatus("Not Live");
				log.setFlag(json.getString("flag"));
				try
				{
					db.save(log);
				}
				catch(Exception e)
				{
					throw new Exception("Bad entry into db",e);
				}
				
				
				
			}
			 catch (JSONException e) {
				
				throw new Exception("JSONException occured", e);
			 }
		 
}

	@Override
	public String sendDataToJbpm(Map<Object, Object> m) throws Exception  {
		
		System.out.println("map value is " +m);
		
		HttpEntity<Map<Object, Object>> entity1 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("PEMaker", "Welcome1"));
	
		String url1 = "http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/processes/Payearly_11_Oct.Payearly/instances/";
		
//		HttpEntity<Map<Object, Object>> entity1 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("wbadmin", "wbadmin"));
//		
//		String url1 = "http://localhost:8080/kie-server/services/rest/server/containers/Payearly_New_1.0.0-SNAPSHOT/processes/Payearly_Demo.Payearly/instances/";
////		
		// Process start in jbpm
		ResponseEntity<String> output = null;
		//try {
			output	= restTemplate.exchange (url1, HttpMethod.POST, entity1, String.class);
			
//		} catch (HttpStatusCodeException exception) {
//			
//			System.out.println(">>>>>>>>>"+ exception.getStatusCode());
//			System.out.println("printing" + output.getBody());
//		}
		
		String process_instance_id = output.getBody().toString();
		
		System.out.println("Server JBPM Process has been started with process_instance_id "+ process_instance_id);
		
		
		// Getting Task-Id
	
		String url2 ="http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/processes/instances/"+process_instance_id;
		HttpEntity entity2 = new HttpEntity(ut.createHeaders("PEMaker", "Welcome1"));
		
//		String url2 ="http://localhost:8080/kie-server/services/rest/server/containers/Payearly_New_1.0.0-SNAPSHOT/processes/instances/"+process_instance_id;
//		
//		HttpEntity entity2 = new HttpEntity(ut.createHeaders("jack", "jack"));
		
		// Getting the process info
		ResponseEntity<String> process_detail  = restTemplate.exchange (url2, HttpMethod.GET, entity2, String.class);
		System.out.println(process_detail.getBody().toString());
	
		JSONObject obj = new JSONObject(process_detail);
		ObjectMapper object = new ObjectMapper();
		
		JsonNode root = null;
		try {
			root = object.readTree(process_detail.getBody().toString());
		} catch (IOException e) {
			
			throw new Exception("JSONException occured", e);
		}
				
		// start Task 1
		String url3="http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/states/started";
		HttpEntity entity3 = new HttpEntity(ut.createHeaders("PEMaker", "Welcome1"));
		
//		System.out.println("Task 1 started");
//		String url3="http://localhost:8080/kie-server/services/rest/server/containers/Payearly_New_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/states/started";
//		
//		HttpEntity entity3 = new HttpEntity(ut.createHeaders("jack", "jack"));
		
		ResponseEntity<String> start_task  = restTemplate.exchange (url3, HttpMethod.PUT, entity3, String.class);
		
		System.out.println("Task-id "+root.findValue("task-id")+" has been starterd");
		
		//Completing the Task 1
		String url4="http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/states/completed";
		HttpEntity<Map<Object, Object>> entity4 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("PEMaker", "Welcome1"));
		
		
		
//		
//		String url4="http://localhost:8080/kie-server/services/rest/server/containers/Payearly_New_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/states/completed";
////				
//		HttpEntity<Map<Object, Object>> entity4 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("jack", "jack"));
		
		m.put("Process_id", root.findValue("process-id"));
		m.put("process-instance-id", root.findValue("process-instance-id"));
		m.put("parent-instance-id", root.findValue("parent-instance-id"));
		
		
		ResponseEntity<String> end_task  = restTemplate.exchange (url4, HttpMethod.PUT, entity4, String.class);
		System.out.println("Task-id "+root.findValue("task-id")+" has been Ended");		
		
		HttpEntity<Map<Object, Object>> entity5 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("PEMaker", "Welcome1"));
		
		String url5="http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/contents/output";		
	
//		String url5="http://localhost:8080/kie-server/services/rest/server/containers/Payearly_New_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/contents/output";
//		
//		HttpEntity<Map<Object, Object>> entity5 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("jack", "jack"));
		
		ResponseEntity<String> get_output_1  = restTemplate.exchange (url5, HttpMethod.GET, entity5, String.class);
		
		System.out.println("Output of Approval 1 "+get_output_1.getBody());
		
		return get_output_1.getBody();
	
	}


	@Override
	public EntityAndAddressDetailDTO getEntityDetail(String id) {
		
		EntityAndAddressDetailDTO addressDetailDTO = new EntityAndAddressDetailDTO();
		EntityDetails entityDetails = entityDetailsReposoitory.findBygstin(id);
		List<EntityAddress> entityAddresses = entityAddressRepository.findAllByEntityDetails_id(entityDetails.getId());
		addressDetailDTO.setDetails(entityDetails);
		addressDetailDTO.setAddresses(entityAddresses);
		return addressDetailDTO;
	}


	@Override
	public EntityDetails getEntityDetail(EntityAndAddressDetailDTO entityDetails) {
		// TODO Auto-generated method stub
		
		
	       Optional<EntityDetails> details = entityDetailsReposoitory.findById(entityDetails.getDetails().getId());
	       
	       
	       details.get().setConstitution(entityDetails.getDetails().getConstitution());
	      // details.get().setConstitution(entityDetails.getDetails().getConstitution());
	       details.get().setEntityPan(entityDetails.getDetails().getEntityPan());
	       details.get().setEntityName(entityDetails.getDetails().getEntityName());
	       details.get().setEntityWebsite(entityDetails.getDetails().getEntityName());
	       
	       details.get().setEntityTan(entityDetails.getDetails().getEntityTan());
	       EntityDetails details2 =  entityDetailsReposoitory.save(details.get());
	       
	       entityDetails.getAddresses().stream().forEach(address -> {
		       Optional<EntityAddress>  entityAddress= entityAddressRepository.findById(address.getId());

		       entityAddress.get().setBuildingName(address.getBuildingName());
		       
		       entityAddress.get().setBuildingNo(address.getBuildingNo());
		       entityAddress.get().setCity(address.getCity());
		       entityAddress.get().setDistrict(address.getDistrict());
		       entityAddress.get().setFlateNo(address.getFlateNo());
		       entityAddress.get().setEntityDetails(details2);
		       entityAddress.get().setLocation(address.getLocation());
		       entityAddress.get().setPincode(address.getPincode());
		       entityAddress.get().setState(address.getState());
		       entityAddressRepository.save(entityAddress.get());
	       });
	       
		return details2;
	}


	@Override
	public void deleteEntityDetail(String getin) {
		// TODO Auto-generated method stub
	Optional<ProcessLog> processLog	= entityCreationRepository.findByGstin(getin);
		if (processLog.isPresent()) {
			entityCreationRepository.delete(processLog.get());
		}
	  EntityDetails entityDetails = entityDetailsReposoitory.findBygstin(getin);
	  List<EntityAddress> entityAddresses = entityAddressRepository.findAllByEntityDetails_id(entityDetails.getId());
	  if(entityAddresses != null) {
		  entityAddresses.stream().forEach(m -> {
			  entityAddressRepository.delete(m);
		  });
	  }
	  entityDetailsReposoitory.delete(entityDetails);
	}

}
