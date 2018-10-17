package com.codefans.jbossremoting.example01;

import java.util.Date;

import org.jboss.remoting.transporter.TransporterClient;

public class Client {
	public static void main(String[] args) throws Exception {
		DateProcessor dateProcessor = (DateProcessor) TransporterClient
				.createTransporterClient("socket://localhost:5400", DateProcessor.class);
		String formattedDate = dateProcessor.formatDate(new Date());
		System.out.println("Current date: " + formattedDate);
	}
}
