package com.payearly.service;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;

import com.payearly.dto.EntityCreationDTO;
import com.payearly.dto.GstnResponseDTO;

public interface EntityCreationService {

	GstnResponseDTO getGstnResponse(EntityCreationDTO entityCreationDTO) throws IOException, JSONException;
	String sendDataToJbpm(Map<Object, Object> m) ;
	

}
