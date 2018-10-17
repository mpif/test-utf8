package com.codefans.java.classes;

import java.util.HashMap;

public class MyCompany {

	public String companyName;

	protected MyCompanyDomain myCompanyDomain;

	protected MyCompanyDomain[] myCompanyDomains;

	protected HashMap map;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public MyCompanyDomain getMyCompanyDomain() {
		return myCompanyDomain;
	}

	public void setMyCompanyDomain(MyCompanyDomain myCompanyDomain) {
		this.myCompanyDomain = myCompanyDomain;
	}

	public HashMap getMap() {
		return map;
	}

	public void setMap(HashMap map) {
		this.map = map;
	}

	public MyCompanyDomain[] getMyCompanyDomains() {
		return myCompanyDomains;
	}

	public void setMyCompanyDomains(MyCompanyDomain[] myCompanyDomains) {
		this.myCompanyDomains = myCompanyDomains;
	}

}
