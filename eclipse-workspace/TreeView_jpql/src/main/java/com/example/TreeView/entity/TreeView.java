package com.example.TreeView.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TreeView")
public class TreeView {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private int parent_id;
	
	protected TreeView()
	{
		
	}

	
	public TreeView(int id, String name, int parent_id) {
		super();
		this.id = id;
		this.name = name;
		this.parent_id = parent_id;
	}


	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getParent_id() {
		return parent_id;
	}


	@Override
	public String toString() {
		return "TreeViews [id=" + id + ", name=" + name + ", parent_id=" + parent_id + "]";
	}
	
	


}

