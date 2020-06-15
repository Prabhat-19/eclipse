package com.example.TreeView.Service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.TreeView.dao.CourseRepo;
import com.example.TreeView.dao.InstructorRepo;
import com.example.TreeView.entity.Course;
import com.example.TreeView.entity.Instructor;

@Service
@Transactional
public class TreeViewServiceImpl implements TreeViewService{
	
	private InstructorRepo instructorRepo;
	
	private CourseRepo courseRepo;
	
	

	public TreeViewServiceImpl(InstructorRepo instructorRepo, CourseRepo courseRepo) {
		super();
		this.instructorRepo = instructorRepo;
		this.courseRepo = courseRepo;
	}



	@Override
	public Instructor createInst(@Valid Instructor instructor) {
		instructorRepo.save(instructor);
		return instructor;
	}



	@Override
	public Course createCourse(@Valid Course course) {
		courseRepo.save(course);
		return course;
	}



	@Override
	public List<Course> getCourse() {
		
		return courseRepo.findAll();
	}
	
	

	
}
