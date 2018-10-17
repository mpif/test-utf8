package com.codefans.designpattern.prototype.example01;

import java.util.ArrayList;

public class Town implements Cloneable {

	private String cityId;
	private String name;

	private ArrayList<People> people;

	public Town clone() {
		Town town = null;
		try {
			town = (Town) super.clone();
			town.people = (ArrayList<People>) this.people.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return town;
	}

	public void print() {
		System.out.println("cityId: " + cityId);
		System.out.println("name: " + name);
		System.out.println("people: ");
		if (people != null) {
			People p = null;
			for (int i = 0; i < people.size(); i++) {
				p = people.get(i);
				p.print();
			}
		}
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<People> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<People> people) {
		this.people = people;
	}
}
