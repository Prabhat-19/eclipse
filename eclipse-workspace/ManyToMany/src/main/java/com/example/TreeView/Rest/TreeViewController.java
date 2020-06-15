package com.example.TreeView.Rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TreeView.Service.TreeViewService;
import com.example.TreeView.dao.TreeViewRepository;
import com.example.TreeView.entity.Course;
import com.example.TreeView.entity.Student;

@CrossOrigin(origins = "http://localhost:8102")
@RestController
@RequestMapping("/fetch")

public class TreeViewController {

	@Autowired
	private TreeViewRepository ts;
	
	private TreeViewService service;
//	private TreeViewService treeViewService;
//	
//	@Autowired
//	public TreeViewController(TreeViewService treeViewService)
//	{
//		this.treeViewService = treeViewService;
//	}
	
	@GetMapping("/save")
	public String save()
	{
		// create a course
		Course tempCourse = new Course("DB");
	
		Student tempStudent1 = new Student("John1", "Doe1", "DB@luv2code.com");
		Student tempStudent2 = new Student("Mary2", "Public2", "DB@luv2code.com");
		
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		ts.save(tempCourse);
		
		
		
		
		
		return "Object have been saved";
	}
	
	@GetMapping("/test")
	public String test(String name)
	{
		return service.check(name);
	}
	
	
}
