package com.codefans.jbossremoting.example03;

import java.util.ArrayList;
import java.util.List;

import org.jboss.remoting.transporter.TransporterClient;

public class Client {
	private String locatorURI = "socket://localhost:5400/?serializationtype=jboss";

	public void makeClientCall() throws Exception {
		Order order = createOrder();

		OrderProcessor orderProcessor = (OrderProcessor) TransporterClient.createTransporterClient(locatorURI,
				OrderProcessor.class);

		System.out.println("Order to be processed: " + order);
		Order changedOrder = orderProcessor.processOrder(order);
		System.out.println("Order now processed " + changedOrder);

		TransporterClient.destroyTransporterClient(orderProcessor);

	}

	private Order createOrder() {
		Order order = new Order();
		Customer customer = createCustomer();
		order.setCustomer(customer);

		List items = new ArrayList();
		items.add("Xbox 360");
		items.add("Wireless controller");
		items.add("Ghost Recon 3");

		order.setItems(items);

		return order;
	}

	private Customer createCustomer() {
		Customer cust = new Customer();
		cust.setFirstName("Bob");
		cust.setLastName("Smith");
		Address addr = new Address();
		addr.setStreet("101 Oak Street");
		addr.setCity("Atlanata");
		addr.setState("GA");
		addr.setZip(30249);
		cust.setAddr(addr);

		return cust;
	}

	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.makeClientCall();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
