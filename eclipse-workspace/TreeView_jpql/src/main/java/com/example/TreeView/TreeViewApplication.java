package com.example.TreeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.TreeView.Service.TreeViewServiceImpl;
import com.example.TreeView.Structure.Node;
import com.example.TreeView.dao.TreeViewRepository;
import com.example.TreeView.entity.TreeView;

@SpringBootApplication
public class TreeViewApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(TreeViewApplication.class, args);
		
		//List<TreeView> list = 
		
		//Node<String> root = createTree();
		// printTree(root, " ");
	}
	/*public static Node<String> createTree() {
		 Node<String> root = new Node<>("root");
		 
		 Node<String> N1 = root.addChild(new Node<String>("N1"));
		 
		 Node<String> N11 = N1.addChild(new Node<String>("N11"));
		 Node<String> N12 = N1.addChild(new Node<String>("N12"));
		 Node<String> N13 = N1.addChild(new Node<String>("N13"));
		 
		 Node<String> N2 = root.addChild(new Node<String>("N2"));
		 
		 Node<String> N3 = root.addChild(new Node<String>("N3"));
		 
		 Node<String> N31 = N3.addChild(new Node<String>("N31"));
		 Node<String> N32 = N3.addChild(new Node<String>("N32"));
		 
		 return root;
		 }
	 
	 private static <T> void printTree(Node<T> node, String appender) {
		   System.out.println(appender + node.getData());
		   node.getChildren().forEach(each ->  printTree(each, appender + appender));
		 }*/
	
			
		
	

}
