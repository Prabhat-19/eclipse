package com.example.TreeView.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TreeView.Service.TreeViewService;
import com.example.TreeView.dao.TreeViewRepository;
import com.example.TreeView.entity.TreeView;

@CrossOrigin(origins = "http://localhost:8102")
@RestController
@RequestMapping("/fetch")

public class TreeViewController {

	@Autowired
	private TreeViewRepository ts;
	
	private TreeViewService treeViewService;
	
	public TreeViewController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public TreeViewController(@Lazy TreeViewService treeViewService)
	{
		this.treeViewService = treeViewService;
	}
	
	@GetMapping("/all")
	public List<TreeView> getAllRow()
	{
			return treeViewService.findAll();
	}
	
	@GetMapping("/{id}")
	public TreeView findById(@PathVariable("id") int id )
	{
//		System.out.println(treeViewService.findById(id).getId()+" , "+treeViewService.findById(id).getName()+" , "+treeViewService.findById(id).getParent_id());
		
		return treeViewService.findById(id);
		
	}
	
	@GetMapping("/filter")
	public String getAllRows()
	{
			return ts.find();
	}
	
	@GetMapping("/jpql")
	public List jpql()
	{
			return ts.findByFirstName("N1");
	}
	
}
