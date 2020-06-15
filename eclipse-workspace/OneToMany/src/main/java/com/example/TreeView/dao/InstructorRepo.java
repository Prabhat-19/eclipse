package com.example.TreeView.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TreeView.entity.Instructor;

public interface InstructorRepo extends JpaRepository<Instructor, Integer>{

}
