package com.codefans.designpattern.prototype.example01;

import java.util.ArrayList;

public class City implements Cloneable {

	private String provinceId;
	private String name;
	private ArrayList<Town> towns;

	public City clone() {
		City city = null;
		try {
			city = (City) super.clone();
			city.towns = (ArrayList<Town>) this.towns.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return city;
	}
}
