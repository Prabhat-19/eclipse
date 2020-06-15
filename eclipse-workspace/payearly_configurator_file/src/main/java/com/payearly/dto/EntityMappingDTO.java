package com.payearly.dto;

public class EntityMappingDTO {

	private String entityName;
	
	private String entityid;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	
	public String getEntityid() {
		return entityid;
	}
	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}
	@Override
	public String toString() {
		return "EntityMappingDTO [entityName=" + entityName + ", entityid=" + entityid + "]";
	}
	
	
}
