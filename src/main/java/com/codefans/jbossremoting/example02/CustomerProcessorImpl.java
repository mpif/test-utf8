package com.codefans.jbossremoting.example02;

import java.util.Random;

public class CustomerProcessorImpl implements CustomerProcessor {
	/**
	 * Takes the customer passed, and if not null and customer id is less than
	 * 0, will create a new random id and set it. The customer object returned
	 * will be the modified customer object passed.
	 *
	 * @param customer
	 * @return
	 */
	public Customer processCustomer(Customer customer) {
		if (customer != null && customer.getCustomerId() < 0) {
			customer.setCustomerId(new Random().nextInt(1000));
		}
		System.out.println("processed customer with new id of " + customer.getCustomerId());
		return customer;
	}
}
