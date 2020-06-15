package com.payearly.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONException;

import com.payearly.dto.EntityAndAddressDetailDTO;
import com.payearly.dto.EntityCreationDTO;
import com.payearly.dto.GstnResponseDTO;
import com.payearly.model.EntityDetails;
import com.payearly.web.rest.errors.EntityNotFondException;
import com.payearly.web.rest.errors.GstinNotFoundException;

public interface EntityCreationService {

	GstnResponseDTO getGstnResponse(EntityCreationDTO entityCreationDTO) throws IOException, JSONException, GstinNotFoundException, EntityNotFondException;
	String sendDataToJbpm(Map<Object, Object> m) throws Exception ;
	
	
	void sendData(Map<Object, Object> m) throws EntityNotFondException, Exception;
	
	EntityAndAddressDetailDTO getEntityDetail(String id);
	
	EntityDetails getEntityDetail(EntityAndAddressDetailDTO entityDetails);
	void deleteEntityDetail(String getn);
}
