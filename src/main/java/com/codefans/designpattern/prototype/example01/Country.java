package com.codefans.designpattern.prototype.example01;

import java.util.Date;

public class Country implements Cloneable {

	private String name;
	private Date build;
	private Province province;

	public Country clone() {
		Country country = null;
		try {
			country = (Country) super.clone();
			country.province = this.province.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return country;
	}
}
