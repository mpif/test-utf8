package com.codefans.designpattern.prototype.example01;

public class People implements Cloneable {

	private String name;
	private int sex;
	private int age;

	public People clone() {
		People people = null;
		try {
			people = (People) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return people;
	}

	public void print() {
		System.out.println("name: " + name + ", sex: " + sex + ", age: " + age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
