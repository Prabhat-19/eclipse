package com.payearly.model;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Component
@Entity
@Table(name="Process_Log" , schema = "payearly")
@JsonIgnoreType(value = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProcessLog {

	@Id
	
	private int Process_Instance_Id;
	
	@Column(name="Process_Id")
	private String Process_Id;
	
	
	@Column(name="Parent_Instance_Id")
	private int Parent_Instance_Id;
	
	@Column(name="Gstin")
	private String Gstin;
	
	@Column(name="EntityName")
	private String EntityName;
	
	@Column(name="Status")
	private String Status;
	
	@Column(name="entitypan")
	private String entityPAN;
	
	@Column(name="EntityState")
	private String EntityState;
	
	@Column(name="flag")
	private String flag;
	
	
	@CreatedDate
	@Column(name="created_date", updatable=false)
	@JsonIgnore
	private Instant createdDate = Instant.now();
	
	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	
	public ProcessLog() {
		// TODO Auto-generated constructor stub
	}

	public String getProcess_Id() {
		return Process_Id;
	}

	public void setProcess_Id(String process_Id) {
		Process_Id = process_Id;
	}

	public int getProcess_Instance_Id() {
		return Process_Instance_Id;
	}

	public void setProcess_Instance_Id(int process_Instance_Id) {
		Process_Instance_Id = process_Instance_Id;
	}

	public int getParent_Instance_Id() {
		return Parent_Instance_Id;
	}

	public void setParent_Instance_Id(int parent_Instance_Id) {
		Parent_Instance_Id = parent_Instance_Id;
	}

	public String getGstin() {
		return Gstin;
	}

	public void setGstin(String gstin) {
		Gstin = gstin;
	}

	public String getEntityName() {
		return EntityName;
	}

	public void setEntityName(String entityName) {
		EntityName = entityName;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	
	public String getEntityPAN() {
		return entityPAN;
	}

	public void setEntityPAN(String entityPAN) {
		this.entityPAN = entityPAN;
	}

	public String getEntityState() {
		return EntityState;
	}

	public void setEntityState(String entityState) {
		EntityState = entityState;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		if(flag.equalsIgnoreCase("200"))
		{
			this.flag= "1";
		}
		else if(flag.equalsIgnoreCase("400"))
		{
			this.flag="3";
		}
		else
		{
			this.flag="2";
		}
	}

	@Override
	public String toString() {
		return "Process_Log [Process_Id=" + Process_Id + ", Process_Instance_Id=" + Process_Instance_Id
				+ ", Parent_Instance_Id=" + Parent_Instance_Id + ", Gstin=" + Gstin + ", EntityName=" + EntityName
				+ ", Status=" + Status + ", EntityPAN=" + entityPAN + ", EntityState=" + EntityState + "]";
	}
	
	
}
