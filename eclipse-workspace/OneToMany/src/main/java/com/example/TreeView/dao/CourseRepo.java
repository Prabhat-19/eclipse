package com.example.TreeView.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.TreeView.entity.Course;


//import com.example.TreeView.jpa.TreeViews;


public interface CourseRepo extends JpaRepository<Course,Integer>
{
	
	

}
