package com.codefans.activemq.test.ex01;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/*
 * @Author: Sean
 * @Time: 2015-05-14 10:56:31
 */
public class ProducerRunnable implements Runnable {

	private PreauditEmailMessage pemsg;
	private static int index;

	public ProducerRunnable(PreauditEmailMessage pemsg) {
		this.pemsg = pemsg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {

			// String queueConf = "conf/activemq.xml";
			// String queueConf = "conf/activemq_simple.xml";
			String queueConf = "D:\\git\\test-utf8\\conf\\activemq_simple.xml";
			String confFilePath = "xbean:file:" + queueConf;// build/conf/activemq.xml

			// String thePath =
			// "file://D:/git/test-utf8/conf/activemq_simple.xml";
			String thePath = new File("D:/git/test-utf8/conf/activemq_simple.xml").toURI().toString();
			// thePath = URLEncoder.encode(thePath, "UTF-8");

			// System.out.println("AMQ Config File Path: "+confFilePath);
			// URI brokerURI = new URI(confFilePath);
			// URI brokerURI = new URI(queueConf);
			URI brokerURI = new URI(thePath);

			// Create a ConnectionFactory
			// ActiveMQConnectionFactory connectionFactory = new
			// ActiveMQConnectionFactory("vm://localhost");
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURI);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("C:/TEST.FOO");

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			// producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// Create a messages
			ObjectMessage message = session.createObjectMessage(pemsg);

			// Tell the producer to send the message
			System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName()
					+ ", msg index:[" + (index++) + "], time:[" + System.currentTimeMillis() + "]");
			producer.send(message);

			// Clean up
			session.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}

}
