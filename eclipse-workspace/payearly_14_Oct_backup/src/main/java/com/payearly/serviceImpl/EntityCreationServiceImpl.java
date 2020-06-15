package com.payearly.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payearly.dto.EntitiyAddressDTO;
import com.payearly.dto.EntityCreationDTO;
import com.payearly.dto.GstnResponseDTO;
import com.payearly.service.EntityCreationService;
import com.payearly.utills.UtillsClass;

@Service
public class EntityCreationServiceImpl implements EntityCreationService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UtillsClass ut;
		
	
	@Override
	public GstnResponseDTO getGstnResponse(EntityCreationDTO entityCreationDTO) throws IOException, JSONException {

		String baseUri = "https://api.taxprogsp.co.in/commonapi/v1.1/search?aspid=1625311919&password=15Aug1947@&action=TP&Gstin="+entityCreationDTO.getGstin();

		HttpHeaders headers =new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> reqentity = new HttpEntity<>(headers);
		ResponseEntity<String> response =null;

		try {
			response = restTemplate.exchange(baseUri,HttpMethod.GET,reqentity, String.class);
		}catch(Exception ex) {
			System.out.println("Printing the response" +ex);
		}

	//	System.out.println( "response  >>>>>>>>>>>>>>>>>>>: "+ (response.getStatusCodeValue() == 200)  );
		

		JSONObject obj = new JSONObject(response);
		System.out.println(">>>>>>>>>>>>"+ obj);


		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(response.getBody().toString());


		GstnResponseDTO gstnResponseDTO = new GstnResponseDTO();
		gstnResponseDTO.setEntityName(root.findValue("lgnm").toString());
		gstnResponseDTO.setGstin(entityCreationDTO.getGstin());
		gstnResponseDTO.setEntityPAN(entityCreationDTO.getGstin().substring(2,12));
		gstnResponseDTO.setEntityPIN(root.findValue("pncd").toString());
		gstnResponseDTO.setEntityState(root.findValue("stcd").toString());
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
		
		gstnResponseDTO.setEntityRegisteredAddress(pr_add);
		
		List<EntitiyAddressDTO> addArry = new ArrayList<EntitiyAddressDTO>();
		
		if(root.findValue("adadr") == null) {}
		else {

			JSONArray arry1 = new JSONArray(root.findValue("adadr").findValues("addr"));


			for (int i = 0; i < arry1.length(); ++i) {	
				String str_add = arry1.getString(i);
				ObjectMapper objMapper = new ObjectMapper();
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

				addArry.add(e_add);
			}
		}
		addArry.add(pr_add);
		gstnResponseDTO.setEntityOperatingAddress(addArry);
		System.out.println(gstnResponseDTO);
		return gstnResponseDTO;

	}
	

	@Override
	public String sendDataToJbpm(Map<Object, Object> m)  {
		
		HttpEntity<Map<Object, Object>> entity1 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("PEMaker", "Welcome1"));
		
		//String url1 = "http://localhost:8080/kie-server/services/rest/server/containers/Payearly_New_1.0.0-SNAPSHOT/processes/Payearly_Demo.Payearly/instances/";
		String url1 = "http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/processes/Payearly_11_Oct.Payearly/instances/";
		
		// Process start in jbpm
		ResponseEntity<String> output = restTemplate.exchange (url1, HttpMethod.POST, entity1, String.class);
		String process_instance_id = output.getBody();
		
		System.out.println("Server JBPM Process has been started with process_instance_id "+ process_instance_id);
		
		
		// Getting Task-Id
		
		//String url2 ="http://localhost:8080/kie-server/services/rest/server/containers/Payearly_New_1.0.0-SNAPSHOT/processes/instances/"+process_instance_id;
		String url2 ="http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/processes/instances/"+process_instance_id;
		HttpEntity entity2 = new HttpEntity(ut.createHeaders("PEMaker", "Welcome1"));
		
		// Getting the process info
		ResponseEntity<String> process_detail  = restTemplate.exchange (url2, HttpMethod.GET, entity2, String.class);
		System.out.println(process_detail.getBody().toString());
	
		JSONObject obj = new JSONObject(process_detail);
		ObjectMapper object = new ObjectMapper();
		
		JsonNode root = null;
		try {
			root = object.readTree(process_detail.getBody().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		// start Task 1
		String url4="http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/states/started";
		HttpEntity entity3 = new HttpEntity(ut.createHeaders("PEMaker", "Welcome1"));
		
		ResponseEntity<String> start_task  = restTemplate.exchange (url4, HttpMethod.PUT, entity3, String.class);
		
		System.out.println("Task-id "+root.findValue("task-id")+" has been starterd");
		
		//Completing the Task 1
		String url5="http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/states/completed";				
				
		HttpEntity<Map<Object, Object>> entity4 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("PEMaker", "Welcome1"));
		
		m.put("Process_id", root.findValue("process-id"));
		m.put("process-instance-id", root.findValue("process-instance-id"));
		m.put("parent-instance-id", root.findValue("parent-instance-id"));
		
		
		ResponseEntity<String> end_task  = restTemplate.exchange (url5, HttpMethod.PUT, entity4, String.class);
		System.out.println("Task-id "+root.findValue("task-id")+" has been Ended");		
		
		HttpEntity<Map<Object, Object>> entity5 = new HttpEntity<Map<Object,Object>>(m,ut.createHeaders("PEMaker", "Welcome1"));
		
		String url8="http://192.168.0.145:8081/kie-server/services/rest/server/containers/Payearly_11_Oct_1.0.0-SNAPSHOT/tasks/"+root.findValue("task-id")+"/contents/output";		
		
		ResponseEntity<String> get_output_1  = restTemplate.exchange (url8, HttpMethod.GET, entity5, String.class);
		
		System.out.println("Output of Approval 1 "+get_output_1.getBody());
		
		return get_output_1.getBody();
	
	}

}
