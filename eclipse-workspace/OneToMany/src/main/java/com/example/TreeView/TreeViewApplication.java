package com.example.TreeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.TreeView.Service.TreeViewServiceImpl;
import com.example.TreeView.Structure.Node;
import com.example.TreeView.dao.CourseRepo;


@SpringBootApplication
public class TreeViewApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(TreeViewApplication.class, args);
			
		
	}

}
