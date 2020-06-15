package com.payearly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="Process_Log" , schema = "payearly")
public class Process_Log {

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
	
	@Column(name="EntityPAN")
	private String EntityPAN;
	
	@Column(name="EntityState")
	private String EntityState;
	
	public Process_Log() {
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
		return EntityPAN;
	}

	public void setEntityPAN(String entityPAN) {
		EntityPAN = entityPAN;
	}

	public String getEntityState() {
		return EntityState;
	}

	public void setEntityState(String entityState) {
		EntityState = entityState;
	}

	@Override
	public String toString() {
		return "Process_Log [Process_Id=" + Process_Id + ", Process_Instance_Id=" + Process_Instance_Id
				+ ", Parent_Instance_Id=" + Parent_Instance_Id + ", Gstin=" + Gstin + ", EntityName=" + EntityName
				+ ", Status=" + Status + ", EntityPAN=" + EntityPAN + ", EntityState=" + EntityState + "]";
	}
	
	
}
