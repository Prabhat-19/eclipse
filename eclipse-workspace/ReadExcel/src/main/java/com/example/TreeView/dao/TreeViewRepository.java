package com.example.TreeView.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TreeView.entity.TreeView;

//import com.example.TreeView.jpa.TreeViews;


public interface TreeViewRepository extends JpaRepository<TreeView,Integer>, TreeViewRepositoryCustom
{
	
	

}
