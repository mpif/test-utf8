package com.codefans.jbossremoting.example02;

import org.jboss.remoting.transporter.TransporterClient;

public class Client {
	private String locatorURI = "socket://localhost:5400";

	public void makeClientCall() throws Exception {
		Customer customer = createCustomer();

		CustomerProcessor customerProcessor = (CustomerProcessor) TransporterClient.createTransporterClient(locatorURI,
				CustomerProcessor.class);

		System.out.println("Customer to be processed: " + customer);
		Customer processedCustomer = customerProcessor.processCustomer(customer);
		System.out.println("Customer is now: " + processedCustomer);

		TransporterClient.destroyTransporterClient(customerProcessor);
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
