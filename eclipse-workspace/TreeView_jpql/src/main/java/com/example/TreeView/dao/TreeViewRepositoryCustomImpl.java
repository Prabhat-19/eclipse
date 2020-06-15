package com.example.TreeView.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.TreeView.entity.TreeView;

public class TreeViewRepositoryCustomImpl implements TreeViewRepositoryCustom {

	@PersistenceContext
    EntityManager entityManager;
	
	
	//Custom Query
	public String find() {
		// TODO Auto-generated method stub
		
		Query query = entityManager.createNativeQuery("select * from tree_view where parent_id='1'", TreeView.class);
		
		List<TreeView> t = query.getResultList();
		
		for(TreeView l : t )
		{
			System.out.println("Child nodes of N1 are : "+l.getName());
		}
		System.out.println(" I am inside TreeViewRepositoryCustomImpl");
		return null;
	}

	public List findByFirstName(String firstName) {
	List<TreeView> name = findByFirstName(firstName);
	name.forEach(System.out::println);
	System.out.println("I am inside jpql");
	
	return null;
	}

}
