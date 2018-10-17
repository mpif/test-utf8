package com.codefans.designpattern.prototype.example01;

import java.util.ArrayList;

public class TownTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		People p = new People();
		p.setName("zhang san");
		p.setSex(0);
		p.setAge(22);

		Town town = new Town();
		town.setName("haha zhen");
		ArrayList<People> list = new ArrayList<People>();
		list.add(p);
		town.setPeople(list);

		town.print();

		Town town2 = town.clone();
		town2.print();
	}

}
