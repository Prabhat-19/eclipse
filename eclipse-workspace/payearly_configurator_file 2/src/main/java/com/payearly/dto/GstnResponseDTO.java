package com.payearly.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GstnResponseDTO {

	private String gstin;
	
	private String entityName;
	
	private String entityPAN;
	
	private EntitiyAddressDTO entityRegisteredAddress;
	
	private List<EntitiyAddressDTO> entityOperatingAddress;
	
	private String entityState;
	
	private String entityPIN;
	private String entityConstitution;

	private Integer Flag;

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityPAN() {
		return entityPAN;
	}

	public void setEntityPAN(String entityPAN) {
		this.entityPAN = entityPAN;
	}

	public EntitiyAddressDTO getEntityRegisteredAddress() {
		return entityRegisteredAddress;
	}

	public void setEntityRegisteredAddress(EntitiyAddressDTO entityRegisteredAddress) {
		this.entityRegisteredAddress = entityRegisteredAddress;
	}

	public List<EntitiyAddressDTO> getEntityOperatingAddress() {
		return entityOperatingAddress;
	}

	public void setEntityOperatingAddress(List<EntitiyAddressDTO> entityOperatingAddress) {
		this.entityOperatingAddress = entityOperatingAddress;
	}

	public String getEntityConstitution() {
		return entityConstitution;
	}

	public void setEntityConstitution(String entityConstitution) {
		this.entityConstitution = entityConstitution;
	}

	public String getEntityState() {
		return entityState;
	}

	public void setEntityState(String entityState) {
		this.entityState = entityState;
	}

	public String getEntityPIN() {
		return entityPIN;
	}

	
	public Integer getFlag() {
		return Flag;
	}

	public void setFlag(Integer flag) {
		Flag = flag;
	}

	public void setEntityPIN(String entityPIN) {
		this.entityPIN = entityPIN;
	}

	@Override
	public String toString() {
		return "GstnResponseDTO [gstin=" + gstin + ", entityName=" + entityName + ", entityPAN=" + entityPAN
				+ ", entityRegisteredAddress=" + entityRegisteredAddress + ", entityOperatingAddress="
				+ entityOperatingAddress + ", entityState=" + entityState + ", entityPIN=" + entityPIN
				+ ", entityConstitution=" + entityConstitution + "]";
	}
}