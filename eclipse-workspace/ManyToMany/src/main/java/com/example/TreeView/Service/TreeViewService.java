package com.example.TreeView.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TreeView.entity.Student;

public interface TreeViewService  {

	void save();
	
	String check(String name);
	
	
	

}
