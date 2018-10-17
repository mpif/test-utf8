package com.codefans.activemq.test.ex01;

import java.net.URI;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/*
 * @Author: Sean
 * @Time: 2015-05-14 10:43:59
 */
public class ConsumerRunnable implements Runnable, ExceptionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {

			// String queueConf = "conf/activemq.xml";
			String queueConf = "conf/activemq_simple.xml";
			String confFilePath = "xbean:file:" + queueConf;// build/conf/activemq.xml

			// System.out.println("AMQ Config File Path: "+confFilePath);
			URI brokerURI = new URI(confFilePath);

			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
			// ActiveMQConnectionFactory connectionFactory = new
			// ActiveMQConnectionFactory(brokerURI);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			connection.setExceptionListener(this);

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("C:/TEST.FOO");

			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);

			// Wait for a message
			Message message = consumer.receive(1000);

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Received TextMessage: " + text);
			} else if (message instanceof ObjectMessage) {
				ObjectMessage objMsg = (ObjectMessage) message;
				PreauditEmailMessage pemsg = (PreauditEmailMessage) objMsg.getObject();
				System.out.println("Thread:" + Thread.currentThread().getName() + ", Received ObjectMessage, subject: "
						+ pemsg.getSubject());
			} else {
				System.out.println("Received others: " + message);
			}

			consumer.close();
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}

	public synchronized void onException(JMSException ex) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}

}