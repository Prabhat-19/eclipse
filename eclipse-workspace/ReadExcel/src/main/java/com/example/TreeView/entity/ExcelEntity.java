package com.example.TreeView.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EmpDetails")
public class ExcelEntity implements Marker {
	
	@Override
	public String toString() {
		return "ExcelEntity [id=" + id + ", name=" + name + ", email=" + email + "]";
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String email;
	
	protected ExcelEntity()
	{
		
	}

	
	public ExcelEntity(String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}


	
	


}

