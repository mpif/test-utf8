package com.codefans.jbossremoting.example03;

import java.util.Iterator;
import java.util.List;

public class Order {
	private int orderId = -1;
	private boolean isProcessed = false;
	private Customer customer = null;
	private List items = null;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean processed) {
		isProcessed = processed;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nOrder:\n");
		buffer.append("\nIs processed: " + isProcessed);
		buffer.append("\nOrder id: " + orderId);
		buffer.append(customer.toString());

		buffer.append("\nItems ordered:");
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			buffer.append("\n" + itr.next().toString());
		}

		return buffer.toString();
	}
}
