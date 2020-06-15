package com.example.TreeView.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TreeView.entity.TreeView;

//import com.example.TreeView.jpa.TreeViews;


public interface TreeViewRepository extends JpaRepository<TreeView,Integer>, TreeViewRepositoryCustom
{
	@Query(value ="select * FROM tree_view WHERE name = ?1")
    List<TreeView> findByFirstName(String firstName);
	

}
