package com.codefans.designpattern.prototype;

import java.util.HashMap;
import java.util.Map;

public class Prototype implements Cloneable {

	protected int num;
	protected HashMap map = new HashMap();

	public Prototype clone() {
		Prototype prototype = null;
		try {
			prototype = (Prototype) super.clone();
			prototype.map = (HashMap) this.map.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return prototype;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(HashMap map) {
		this.map = map;
	}
}