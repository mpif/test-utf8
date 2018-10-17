package com.codefans.jbossremoting.example02;

import java.io.Serializable;

public class Customer implements Serializable {
	private String firstName = null;
	private String lastName = null;
	private Address addr = null;
	private int customerId = -1;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nCustomer:\n");
		buffer.append("customer id: " + customerId + "\n");
		buffer.append("first name: " + firstName + "\n");
		buffer.append("last name: " + lastName + "\n");
		buffer.append("street: " + addr.getStreet() + "\n");
		buffer.append("city: " + addr.getCity() + "\n");
		buffer.append("state: " + addr.getState() + "\n");
		buffer.append("zip: " + addr.getZip() + "\n");

		return buffer.toString();
	}
}
