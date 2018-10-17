package com.codefans.jbossremoting.example03;

public interface CustomerProcessor {
	/**
	 * Process a customer object. Implementors should ensure that the customer
	 * object passed as parameter should have its internal state changed somehow
	 * and returned.
	 *
	 * @param customer
	 * @return
	 */
	public Customer processCustomer(Customer customer);
}
