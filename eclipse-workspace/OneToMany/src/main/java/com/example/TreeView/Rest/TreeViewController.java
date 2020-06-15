package com.example.TreeView.Rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TreeView.Service.TreeViewService;
import com.example.TreeView.dao.CourseRepo;
import com.example.TreeView.entity.Course;
import com.example.TreeView.entity.Instructor;

@CrossOrigin(origins = "http://localhost:8102")
@RestController
@RequestMapping("/fetch")

public class TreeViewController {
	
	private TreeViewService treeViewService;
	
	

	public TreeViewController(TreeViewService treeViewService) {
		super();
		this.treeViewService = treeViewService;
	}



	@PostMapping("/saveInstructor")
	public ResponseEntity<Instructor> createInst(@Valid @RequestBody Instructor instructor)
	{
		Instructor inst = treeViewService.createInst(instructor);
		return new ResponseEntity<Instructor>(inst, HttpStatus.OK);
	}
	
	@PostMapping("/saveCourse")
	public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course)
	{
		Course c = treeViewService.createCourse(course);
		return new ResponseEntity<Course>(c, HttpStatus.OK);
	}
	
	@GetMapping("/getCourses")
	public ResponseEntity<List<Course>> getCourses()
	{
		List<Course> c = treeViewService.getCourse();
		return new ResponseEntity<List<Course>>(c, HttpStatus.OK);
	}
}
