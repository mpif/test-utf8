package com.codefans.designpattern.prototype.example01;

import java.util.ArrayList;

public class Province implements Cloneable {

	private String countryId;
	private String name;

	private ArrayList<City> cities;

	public Province clone() {
		Province province = null;
		try {
			province = (Province) super.clone();
			province.cities = (ArrayList<City>) this.cities.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return province;
	}
}
