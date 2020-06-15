package com.example.TreeView.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TreeView.entity.Course;
import com.example.TreeView.entity.Instructor;



public interface TreeViewService  {

	Instructor createInst(@Valid Instructor instructor);

	Course createCourse(@Valid Course course);

	List<Course> getCourse();
	
	
	

}
