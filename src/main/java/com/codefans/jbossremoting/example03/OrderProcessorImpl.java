package com.codefans.jbossremoting.example03;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class OrderProcessorImpl implements OrderProcessor {
	private CustomerProcessor customerProcessor = null;

	public OrderProcessorImpl() {
		customerProcessor = new CustomerProcessorImpl();
	}

	public Order processOrder(Order order) {
		System.out.println("Incoming order to process from customer.\n" + order.getCustomer());

		// has this customer been processed?
		if (order.getCustomer().getCustomerId() < 0) {
			order.setCustomer(customerProcessor.processCustomer(order.getCustomer()));
		}

		List items = order.getItems();
		System.out.println("Items ordered:");
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

		order.setOrderId(new Random().nextInt(1000));
		order.setProcessed(true);

		System.out.println("Order processed.  Order id now: " + order.getOrderId());
		return order;
	}
}
