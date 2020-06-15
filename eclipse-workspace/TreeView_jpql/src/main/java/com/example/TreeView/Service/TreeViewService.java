package com.example.TreeView.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TreeView.entity.TreeView;

public interface TreeViewService  {
	
	public List<TreeView> findAll();

	public TreeView findById(int id);

	public String find();

}
