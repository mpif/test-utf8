package com.codefans.java.classes;

import java.util.HashMap;

public class Company {

	public int companyId;

	public String companyName;

	protected CompanyDomain companyDomain;

	protected CompanyDomain[] companyDomains;

	protected HashMap map;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CompanyDomain getCompanyDomain() {
		return companyDomain;
	}

	public void setCompanyDomain(CompanyDomain companyDomain) {
		this.companyDomain = companyDomain;
	}

	public HashMap getMap() {
		return map;
	}

	public void setMap(HashMap map) {
		this.map = map;
	}

	public CompanyDomain[] getCompanyDomains() {
		return companyDomains;
	}

	public void setCompanyDomains(CompanyDomain[] companyDomains) {
		this.companyDomains = companyDomains;
	}

}
