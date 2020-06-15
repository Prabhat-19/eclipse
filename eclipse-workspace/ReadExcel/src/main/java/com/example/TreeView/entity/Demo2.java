package com.example.TreeView.entity;

public class Demo2 implements Marker {
	
	String name;
	int age;
	
	
	public Demo2(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Demo1 [name=" + name + ", age=" + age + "]";
	}
	

}
